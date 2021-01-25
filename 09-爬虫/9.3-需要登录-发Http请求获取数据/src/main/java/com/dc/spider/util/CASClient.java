package com.dc.spider.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

/**
 * 模拟登录cas，后获取数据
 */
public class CASClient extends DefaultHttpClient {
    public CASClient() throws Exception {
        super();
        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        ctx.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, ssf));
    }

    /**
     * 获取参数
     *
     * @param httpclient
     * @param url        f12查看的 登录请求地址
     * @return
     * @throws IOException
     */
    private static String doCasLoginRequest(DefaultHttpClient httpclient, String url) throws IOException {
        try {
            String result = "";
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            BufferedReader rd = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            String tempLine = rd.readLine();
            String s = "<input type=\"hidden\" name=\"lt\" value=\"";
            while (tempLine != null) {
                int index = tempLine.indexOf(s);
                if (index != -1) {
                    String s1 = tempLine.substring(index + s.length());
                    int index1 = s1.indexOf("\"");
                    if (index1 != -1)
                        result = s1.substring(0, index1);
                }
                tempLine = rd.readLine();
            }
            if (entity != null) {
                entity.getContent().close();
            }
            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * @param url
     * @param userName 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    public static DefaultHttpClient casLogin(String url, String userName, String password) throws Exception {
        DefaultHttpClient httpClient = new CASClient();
        httpClient.getParams().setParameter("User-Agent", "Mozilla/5.0");
        HttpPost post = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", userName));
        nvps.add(new BasicNameValuePair("password", password));
        nvps.add(new BasicNameValuePair("lt", doCasLoginRequest(httpClient, url)));
        nvps.add(new BasicNameValuePair("execution", "e1s1"));
        nvps.add(new BasicNameValuePair("_eventId", "submit"));
        nvps.add(new BasicNameValuePair("submit", "登录"));
        post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        HttpResponse response = httpClient.execute(post);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            httpClient.getCookieStore().getCookies().forEach(c -> {
                System.out.println("cookie是：" + c.getName() + "=>" + c.getValue());
            });
            entity.getContent().close();
        }
        return httpClient;
    }

    public static void main(String[] args) throws Exception {

        DefaultHttpClient httpClient = casLogin("http://172.10.8.2:8080/cas/login", "admin", "ahslsj123");

        //登录成功后需要访问的请求  GET请求
        // HttpGet httpGet = new HttpGet("http://172.10.8.2:8080/XTBG/newsrelease/list.do");
        // CloseableHttpResponse response = httpClient.execute(httpGet);
        // String entity = EntityUtils.toString(response.getEntity(), "utf-8");
        // System.out.println(entity);

        // 登录成功后需要访问的请求 POST请求
        HttpPost httpPost = new HttpPost("http://172.10.8.2:8080/ccyw/qyxx/queryQyxxPage.do?qylx=0");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("page", "1"));
        nvps.add(new BasicNameValuePair("rows", "10"));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

        StringBuilder sb = new StringBuilder();
        httpClient.getCookieStore().getCookies().forEach((cookie) -> {
            sb.append(cookie.getName() + "=" + cookie.getValue() + ";");
        });
        String s = sb.toString();
        httpPost.setHeader("Cookie", "JSESSIONID=F18FF3360DE3302B6D7E89980EBFAFB2;");
        // httpPost.setHeader("Cookie", "JSESSIONID=F18FF3360DE3302B6D7E89980EBFAFB2;CASTGC=TGT-20529-yMNV3AUdUoaN7sNSjCbfKxFdrOrtIHZ5mifcc4hoEjmhcO71Gf-cas01.example.org;");
        HttpResponse response2 = httpClient.execute(httpPost);
        String entity2 = EntityUtils.toString(response2.getEntity(), "utf-8");
        System.out.println(entity2);

    }

}
