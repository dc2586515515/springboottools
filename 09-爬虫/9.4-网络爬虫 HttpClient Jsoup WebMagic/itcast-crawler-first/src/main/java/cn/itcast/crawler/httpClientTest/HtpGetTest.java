package cn.itcast.crawler.httpClientTest;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-26
 */
public class HtpGetTest {
    public static void main(String[] args) {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建HttpGet对象, 设置url访问地址
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
            try {
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
