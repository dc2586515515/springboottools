package cn.itcast.jd.task;

import cn.itcast.jd.pojo.Item;
import cn.itcast.jd.service.ItemService;
import cn.itcast.jd.utils.HttpUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-28
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ItemTask {
    @Autowired
    private HttpUtils httpUtils;

    @Autowired
    private ItemService itemService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 当下载任务完成后，间隔多长时间进行下一次的任务
     *
     * @throws Exception
     */
    // @Scheduled(fixedDelay = 10 * 1000)
    @Test
    public void itemTask() throws Exception {
        // 生明需要解析的初始地址
        String utl = "https://search.jd.com/search?keyword=%E6%89%8B%E6%9C%BA&wq=%E6%89%8B%E6%9C%BA&ev=559_103811%5E&s=57&click=0&page=";

        // 按照页面对手机的搜索结果进行遍历解析
        for (int i = 19; i < 20; i = i + 2) {
            String html = httpUtils.doGetHtml(utl + i);
            this.parse(html);
        }

        System.out.println("手机数据抓取完成...");
    }

    /**
     * 解析html页面,获取商品数据并存储，核心逻辑
     *
     * @param html
     */
    private void parse(String html) throws IOException {
        // 解析html获取Document对象
        Document doc = Jsoup.parse(html);

        // 获取spuEles信息
        Elements spuEles = doc.select("div#J_goodsList>ul>li");

        // 遍历spuEles
        for (Element spuEle : spuEles) {
            // 排除没有data-spu的值的内容
            if (StringUtils.isNotEmpty(spuEle.attr("data-spu"))) {
                // 获取spu
                long spu = Long.parseLong(spuEle.attr("data-spu"));

                // 获取sku信息
                Elements skuEles = spuEle.select("ul.ps-main>li.ps-item");
                // 根据sku过去商品数据
                for (Element skuEle : skuEles) {
                    // 获取sku
                    long sku = Long.parseLong(skuEle.select("[data-sku]").first().attr("data-sku"));

                    Item item = new Item();

                    item.setSku(sku);
                    List<Item> list = this.itemService.findAll(item);
                    if (list.size() > 0) {
                        // 如果商品存在，就进行下一个循环
                        continue;
                    }
                    // 设置商品的spu
                    item.setSpu(spu);
                    // 获取商品的详情的url
                    String itemUrl = "https://item.jd.com/" + sku + ".html";
                    item.setUrl(itemUrl);

                    // 获取商品的图片
                    String picUrl = "https:" + skuEle.select("img[data-sku]").first().attr("data-lazy-img");
                    picUrl = picUrl.replace("/n7/", "/n1/");
                    String picName = this.httpUtils.doGetImage(picUrl);
                    item.setPic(picName);

                    //获取商品的价格
                    String priceJson = this.httpUtils.doGetHtml("https://p.3.cn/prices/mgets?skuIds=J_" + sku);
                    double price = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                    item.setPrice(price);

                    // 获取商品的标题
                    String itemInfo = this.httpUtils.doGetHtml(item.getUrl());

                    String title = Jsoup.parse(itemInfo).select("div.sku-name").text();
                    item.setTitle(title);

                    item.setCreated(new Date());
                    item.setUpdated(item.getCreated());

                    // 保存商品数据到数据库中
                    this.itemService.save(item);
                }
            }
        }

    }
}
