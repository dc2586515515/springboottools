package com.dc.spider.task;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 先登录，然后发送 GET 请求获取数据据
 * @Author DC
 * @Date 2021-01-22
 */
public class HttpGetForData02 {
    /**
     * 待完成
     *
     * @param args
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static void main(String[] args) throws ClientProtocolException, IOException {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        // 登录url
        String url = "http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check%3Bjsessionid%3DFB40371DF50AF8E639F110651ABBA0C8&locale=zh_CN";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        // 用户名
        nvp.add(new BasicNameValuePair("username", "admin"));
        // 密码
        nvp.add(new BasicNameValuePair("password", "ahslsj123"));
        nvp.add(new BasicNameValuePair("_eventId", "submit"));
        nvp.add(new BasicNameValuePair("lt", "LT-82138-opgOffuc2YIs0UGzWhWkN6LySjOiXP-cas01.example.org"));
        nvp.add(new BasicNameValuePair("execution", "e1s1"));

        // 设置请求头
        httpost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpost.setHeader("Accept-Encoding", "gzip, deflate");
        httpost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpost.setHeader("Cache-Control", "max-age=0");
        httpost.setHeader("Connection", "keep-alive");
        httpost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpost.setHeader("Cookie", "JSESSIONID=51372509B267A0459CD3B116D34C2ED0");
        httpost.setHeader("Host", "172.10.8.2:8080");
        httpost.setHeader("Origin", "http://172.10.8.2:8080");
        httpost.setHeader("Referer", "http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check%3Bjsessionid%3DF8EB8A266B5FE984AEC679FA381FEBCA");
        httpost.setHeader("Upgrade-Insecure-Requests", "1");
        httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
        // 设置用户名密码
        httpost.setEntity(new UrlEncodedFormEntity(nvp, HTTP.UTF_8));


        HttpResponse response1 = client.execute(httpost);
        httpost.abort();//关闭httppost，不关闭的话下面使用httpget会报错
        if (response1.getStatusLine().getStatusCode() == 302) {//使用httppost执行，会导致302重定向，response中会包含重定向的地址yyy，需使用get方式访问
            // 登录成功后，需要请求的URL
            HttpGet httpget = new HttpGet("http://172.10.8.2:8080/XTBG/j_spring_cas_security_check;jsessionid=FB40371DF50AF8E639F110651ABBA0C8?ticket=ST-26469-WAjWRJIFi2cf4WzGuf23-cas01.example.org");
            httpget.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            httpget.setHeader("Accept-Encoding", "gzip, deflate");
            httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
            httpget.setHeader("Cache-Control", "max-age=0");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("Cookie", "JSESSIONID=F8EB8A266B5FE984AEC679FA381FEBCA; org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE=zh_CN");
            httpget.setHeader("Host", "172.10.8.2:8080");
            httpget.setHeader("Referer", "http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check%3Bjsessionid%3DF8EB8A266B5FE984AEC679FA381FEBCA");
            httpget.setHeader("Upgrade-Insecure-Requests", "1");
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
            // 执行GET请求
            HttpResponse response = client.execute(httpget);
            String entity = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println("************************开始输出获取到的信息****************************");
            System.out.println(entity);//输出的就是html的内容
            System.out.println("************************输出信息结束****************************");

        } else {
            System.out.println("失败");
        }
    }
}
