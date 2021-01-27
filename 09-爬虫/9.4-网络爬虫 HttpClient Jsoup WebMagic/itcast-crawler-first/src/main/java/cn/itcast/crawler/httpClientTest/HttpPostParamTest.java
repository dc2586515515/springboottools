package cn.itcast.crawler.httpClientTest;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author DC
 * @Date 2021-01-26
 */
public class HttpPostParamTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建HttpPost对象, 设置url访问地址
        HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");
        // 声明List集合, 封装表单中的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("keys", "Java"));

        // 创建表单的Entity对象, 第一个参数就是封装好的表单数据, 第二个参数就是编码
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, "utf8");

        // 设置表单的Entity对象到 Post 请求中
        httpPost.setEntity(formEntity);

        CloseableHttpResponse response = null;
        try {
            // 使用 HttpClient 发起请求, 获取 response
            response = httpClient.execute(httpPost);
            // 解析响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println("长度为：" + content.length());
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
