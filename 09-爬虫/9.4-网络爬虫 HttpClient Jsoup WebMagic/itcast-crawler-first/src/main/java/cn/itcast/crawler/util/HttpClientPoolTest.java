package cn.itcast.crawler.util;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-26
 */
public class HttpClientPoolTest {
    public static void main(String[] args) {
        // 创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // 设置最大连接数
        cm.setMaxTotal(100);
        // 设置每个需要访问主机的最大连接数（Host的值，即我们这边访问的是 新浪，百度，还是黑马等等，这些即是我要访问的主机）
        // 比如总的是 100 ，每个主机是10，即我访问新浪最多10个，访问百度最多10个，等等
        cm.setDefaultMaxPerRoute(10);

        // 使用连接池发起请求
        doGet(cm);
        doGet(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {
        // 不是每次创建新的HttpClient, 而是从连接池中获取 HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");

        CloseableHttpResponse response = null;
        try {
            // 使用 HttpClient 发起请求, 获取 response
            response = httpClient.execute(httpGet);
            // 解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭 response
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // 不能关闭 HttpClient, 由连接池管理 HttpClient
                // httpClient. close();
            }
        }

    }
}