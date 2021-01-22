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
public class HttpGetForData01 {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        //初始化一个httpclient
        HttpClient client = new DefaultHttpClient();
        // 登录url
        String url = "http://36.7.135.171:10081/outer-web/j_spring_security_check.do";
        HttpPost httpost = new HttpPost(url);
        List<NameValuePair> nvp = new ArrayList<NameValuePair>();
        // 用户名
        nvp.add(new BasicNameValuePair("j_username", "xxx"));
        // 密码
        nvp.add(new BasicNameValuePair("j_password", "xxx"));
        httpost.setEntity(new UrlEncodedFormEntity(nvp, HTTP.UTF_8));
        HttpResponse response1 = client.execute(httpost);
        httpost.abort();//关闭httppost，不关闭的话下面使用httpget会报错
        if (response1.getStatusLine().getStatusCode() == 302) {     //使用httppost执行，会导致302重定向，response中会包含重定向的地址yyy，需使用get方式访问
            // 登录成功后，需要请求的URL
            HttpGet httpget = new HttpGet("http://36.7.135.171:10081/outer-web/staffInfo/findStaffInfoListPage.do?orgId=");
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
