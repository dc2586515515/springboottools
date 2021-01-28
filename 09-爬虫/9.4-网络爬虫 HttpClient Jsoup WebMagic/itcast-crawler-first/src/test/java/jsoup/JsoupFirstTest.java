package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

/**
 * @Description PS:虽然使用Jsoup可以替代 HttpClient直接发起请求解析数据，但是往往不会这样用，因为实际的开发过程中，
 * * 需要使用到多线程，连接池，代理等等方式，而 jsoup对这些的支持并不是很好，所以我们一般把 jsoup仅仅作为 Html解析工具使用。
 * @Author DC
 * @Date 2021-01-27
 */
public class JsoupFirstTest {

    /**
     * jsoup解析 URL
     *
     * @throws Exception
     */
    @Test
    public void testUrl() throws IOException {
        // 解析url地址, 第一个参数是访问的url, 第二个参数时访问时候的超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn/"), 1000);
        // 使用标签选择器, 获取title标签中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println("标题是：" + title);
    }

    /**
     * 解析字符串
     *
     * @throws Exception
     */
    @Test
    public void testString() throws Exception {
        // 使用工具类读取文件, 获取字符串
        String content = FileUtils.readFileToString(new File("F:\\Learing\\2020Learin\\codespace\\dc\\springboottools\\09-爬虫\\9.4-网络爬虫 HttpClient Jsoup WebMagic\\itcast-crawler-first\\src\\test\\java\\html\\test.html"),
                "utf8");

        // 解析字符串
        Document doc = Jsoup.parse(content);
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    /**
     * Jsoup 解析文件
     *
     * @throws Exception
     */
    @Test
    public void testFile() throws Exception {
        Document doc = Jsoup.parse(new File("F:\\Learing\\2020Learin\\codespace\\dc\\springboottools\\09-爬虫\\9.4-网络爬虫 HttpClient Jsoup WebMagic\\itcast-crawler-first\\src\\test\\java\\html\\test.html"),
                "utf8");

        // 解析文件
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    /**
     * 使用 DOM 方式获取元素
     *
     * @throws Exception
     */
    @Test
    public void testDom() throws Exception {
        // 解析文件件, 获取Document对象
        Document doc = Jsoup.parse(new File("F:\\Learing\\2020Learin\\codespace\\dc\\springboottools\\09-爬虫\\9.4-网络爬虫 HttpClient Jsoup WebMagic\\itcast-crawler-first\\src\\test\\java\\html\\test.html"), "utf8");
        // 1.根据id查询元素getElementById
        // Element element = doc.getElementById("city_bj");

        // 2.根据标签获取元素getElementsByTag
        // Element element = doc.getElementsByTag("span").first();

        // 3.根据class获取元素getElementsByClass
        // Element element = doc.getElementsByClass("class_a class_b").first();
        // Element element = doc.getElementsByClass("class_a").first();
        // Element element = doc.getElementsByClass("class_b").first();

        // 4.根据属性获取元素getElementsByAttribute
        // Element element = doc.getElementsByAttribute("abc").first();

        // 5.根据属性与属性值筛选
        Element element = doc.getElementsByAttributeValue("href", "http://sh.itcast.cn").first();

        // 打印元素内容
        System.out.println("获取到的元素内容是: " + element.text());
    }


    /**
     * 获取元素中的数据
     *
     * @throws Exception
     */
    @Test
    public void testData() throws Exception {
        // 解析文件件, 获取Document对象
        Document doc = Jsoup.parse(new File("F:\\Learing\\2020Learin\\codespace\\dc\\springboottools\\09-爬虫\\9.4-网络爬虫 HttpClient Jsoup WebMagic\\itcast-crawler-first\\src\\test\\java\\html\\test.html"), "utf8");
        // 根据id 获取元素
        Element element = doc.getElementById("test");

        String str = "";
        // 元素中获取数据
        // 1. 从元素中获取 id
        // str = element.id();

        // 2. 从元素中获取 className
        // str = element.className();
        // Set<String> classNames = element.classNames();
        // classNames.forEach(item -> System.out.println(item));

        // 3. 从元素中获取 attr
        // str = element.attr("id");
        // str = element.attr("class");

        // 4. 从元素中获取 所有属性 attributes
        // Attributes attributes = element.attributes();
        // System.out.println(attributes.toString());

        // 5. 从元素中获取 文本内容text
        str = element.text();

        //打印
        System.out.println("获取到的数据是：" + str);
    }

    /**
     * Selector选择器概述
     *
     * @throws Exception
     */
    @Test
    public void testSelector() throws Exception {
        // 解析html文件，获取Document对象
        Document doc = Jsoup.parse(new File("F:\\Learing\\2020Learin\\codespace\\dc\\springboottools\\09-爬虫\\9.4-网络爬虫 HttpClient Jsoup WebMagic\\itcast-crawler-first\\src\\test\\java\\html\\test.html"), "utf8");

        // tagname：通过标签查找元素，比如： span
        // Elements elements = doc.select("span");
        // elements.forEach(element -> System.out.println(element.text()));

        // #id：通过ID查找元素，比如：#city_bj
        // Element element = doc.select("#city_bj").first();
        // System.out.println(element.text());

        // .class：通过 class名称查找元素，比如：.class_a
        // Element element = doc.select(".class_a").first();
        // System.out.println(element.text());

        // [attribute]：利用属性查找元素，比如：[abc]
        // Element element = doc.select("[abc]").first();
        // System.out.println(element.text());

        // [attr=value]：利用属性值来查找元素，比如：[class=s_name]
        Elements elements = doc.select("[class=s_name]");
        elements.forEach(element -> System.out.println(element.text()));
    }
}
