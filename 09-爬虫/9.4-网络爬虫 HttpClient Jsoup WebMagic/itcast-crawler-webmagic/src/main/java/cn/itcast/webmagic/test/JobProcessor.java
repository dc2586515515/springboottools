package cn.itcast.webmagic.test;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-28
 */
public class JobProcessor implements PageProcessor {
    /**
     * 解析页面
     *
     * @param page
     */+
    public void process(Page page) {
        // 解析返回的数据page, 并且把解析的结果放到ResultItems中
        // css表达式
        page.putField("title", page.getHtml().css("title"));

        // XPath
        page.putField("div", page.getHtml().xpath("//div[@id=shortcut-2014]/div/ul/li/div/a/text()"));

        // 正则表达式
        page.putField("a", page.getHtml().css("div#shortcut-2014 a").regex(".*京东.*").all());

    }

    private Site site = Site.me();

    public Site getSite() {
        return site;
    }

    /**
     * 主函数, 执行爬虫
     *
     * @param args
     */
    public static void main(String[] args) {
        Spider.create(new JobProcessor())
                //初始访问url地址
                .addUrl("https://kuaibao.jd.com/")
                .run(); // 执行爬虫
    }
}
