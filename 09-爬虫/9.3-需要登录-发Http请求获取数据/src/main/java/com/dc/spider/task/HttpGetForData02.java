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
     * 1、http://172.10.8.2:8080/XTBG/newsrelease/list.do   302
     * （Location：http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check%3Bjsessionid%3DF0D739FD9A81E144B398DC11221A10E1）
     * <p>
     * 2、http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check%3Bjsessionid%3DF0D739FD9A81E144B398DC11221A10E1    200
     * （
     * Set-Cookie: JSESSIONID=B14C33C12738F37115E66C16C0D4CB10; Path=/cas/; HttpOnly
     * service: http://172.10.8.2:8080/XTBG/j_spring_cas_security_check;jsessionid=45C80E6C37596837ED22BC0B45BD42AD）
     * <p>
     * <p>
     * 3、http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check%3Bjsessionid%3DF0D739FD9A81E144B398DC11221A10E1&locale=zh_CN  302
     * （
     * Location: http://172.10.8.2:8080/XTBG/j_spring_cas_security_check;jsessionid=F0D739FD9A81E144B398DC11221A10E1?ticket=ST-26401-oVBbjbhqrnWn9TQ3uZsI-cas01.example.org
     * service: http://172.10.8.2:8080/XTBG/j_spring_cas_security_check;jsessionid=45C80E6C37596837ED22BC0B45BD42AD
     * locale: zh_CN
     * ）
     * （
     * lt: LT-81059-huMpfwnPCQH6ubsrdYcVduc3XwILXq-cas01.example.org
     * execution: e1s1
     * username: xxx
     * password: xxx
     * <p>
     * ）
     * <p>
     * <p>
     * 4、http://172.10.8.2:8080/XTBG/j_spring_cas_security_check;jsessionid=F0D739FD9A81E144B398DC11221A10E1?ticket=ST-26401-oVBbjbhqrnWn9TQ3uZsI-cas01.example.org    302
     * （
     * <p>
     * Location：http://172.10.8.2:8080/XTBG/newsrelease/list.do
     * <p>
     * Cookie: JSESSIONID=F0D739FD9A81E144B398DC11221A10E1; org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE=zh_CN
     * Host: 172.10.8.2:8080
     * Referer: http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check%3Bjsessionid%3DF0D739FD9A81E144B398DC11221A10E1
     * Upgrade-Insecure-Requests: 1
     * User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36
     * ）
     * <p>
     * <p>
     * <p>
     * 5、http://172.10.8.2:8080/XTBG/newsrelease/list.do   302
     * （
     * Cookie: JSESSIONID=F0D739FD9A81E144B398DC11221A10E1; org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE=zh_CN
     * Host: 172.10.8.2:8080
     * Referer: http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check%3Bjsessionid%3DF0D739FD9A81E144B398DC11221A10E1
     * Upgrade-Insecure-Requests: 1
     * User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.3
     * ）
     */
    public static void main(String[] args) throws ClientProtocolException, IOException {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        // 登录url
        String url = "http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check%3Bjsessionid%3DD7A1CDFF765866CE301A89CF77D451F9&locale=zh_CN";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        // 用户名
        nvp.add(new BasicNameValuePair("username", "xxx"));
        // 密码
        nvp.add(new BasicNameValuePair("password", "xxx"));
        nvp.add(new BasicNameValuePair("_eventId", "submit"));
        nvp.add(new BasicNameValuePair("lt", "LT-80904-EuhtoFdbQU3A7hiiyaeazE5feZFo63-cas01.example.org"));
        nvp.add(new BasicNameValuePair("execution", "LT-80904-EuhtoFdbQU3A7hiiyaeazE5feZFo63-cas01.example.org"));

        // 设置请求头
        httpost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        httpost.setHeader("Accept-Encoding", "gzip, deflate");
        httpost.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpost.setHeader("Cache-Control", "max-age=0");
        httpost.setHeader("Connection", "keep-alive");
        httpost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpost.setHeader("Cookie", "JSESSIONID=FCA3CAAB3B076BED7D8DFE35B462064A");
        httpost.setHeader("Host", "172.10.8.2:8080");
        httpost.setHeader("Origin", "http://172.10.8.2:8080");
        httpost.setHeader("Referer", "http://172.10.8.2:8080/cas/login?service=http%3A%2F%2F172.10.8.2%3A8080%2FXTBG%2Fj_spring_cas_security_check");
        httpost.setHeader("Upgrade-Insecure-Requests", "1");
        httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36");
        // 设置用户名密码
        httpost.setEntity(new UrlEncodedFormEntity(nvp, HTTP.UTF_8));


        HttpResponse response1 = client.execute(httpost);
        // httpost.abort();//关闭httppost，不关闭的话下面使用httpget会报错
        if (response1.getStatusLine().getStatusCode() == 302) {//使用httppost执行，会导致302重定向，response中会包含重定向的地址yyy，需使用get方式访问
            // 再进行一次POST请求
            String url2 = "http://172.10.8.2:8080/XTBG/j_spring_cas_security_check;jsessionid=D7A1CDFF765866CE301A89CF77D451F9?ticket=ST-26328-T39mUW94hOB0c3wUAsJQ-cas01.example.org";
            HttpPost httpost2 = new HttpPost(url2);
            HttpResponse response2 = client.execute(httpost2);
            httpost.abort();//关闭httppost，不关闭的话下面使用httpget会报错
            if (response2.getStatusLine().getStatusCode() == 302) {//使用httppost执行，会导致302重定向，response中会包含重定向的地址yyy，需使用get方式访问
                // 登录成功后，需要请求的URL
                HttpGet httpget = new HttpGet("http://172.10.8.2:8080/XTBG/newsrelease/list.do");
                HttpResponse response = client.execute(httpget);
                String entity = EntityUtils.toString(response.getEntity(), "utf-8");
                System.out.println("************************开始输出获取到的信息****************************");
                System.out.println(entity);//输出的就是html的内容
                System.out.println("************************输出信息结束****************************");
            } else {
                System.out.println("失败");
            }

        } else {
            System.out.println("失败");
        }
    }
}
