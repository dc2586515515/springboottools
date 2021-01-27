package jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-27
 */
public class JsoupFirstTest {

    /**
     * jsoup解析 URL
     * PS:虽然使用Jsoup可以替代 HttpClient直接发起请求解析数据，但是往往不会这样用，因为实际的开发过程中，
     * 需要使用到多线程，连接池，代理等等方式，而 jsoup对这些的支持并不是很好，所以我们一般把 jsoup仅仅作为 Html解析工具使用。
     */
    @Test
    public void testUrl() throws IOException {
        // 解析url地址, 第一个参数是访问的url, 第二个参数时访问时候的超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn/"), 1000);
        // 使用标签选择器, 获取title标签中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println("标题是：" + title);
    }
}
