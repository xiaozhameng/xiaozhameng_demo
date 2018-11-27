package com.xiaozhameng.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaozhameng on 2017/1/17.
 * 使用 httpClient 完成复杂的http请求模拟
 * 访问需要登录的网站从而获取数据
 */
public class HttpClient03 {

    private static final Logger logger = LoggerFactory.getLogger(HttpClient03.class);
   
    public static void main(String[] args) throws Exception {

        List<NameValuePair> paramList = new ArrayList<NameValuePair>();
        paramList.add(new BasicNameValuePair("username","admin"));
        paramList.add(new BasicNameValuePair("password","admin"));


        HttpEntity entity = new UrlEncodedFormEntity(paramList,"utf-8");
        RequestConfig config = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        //创建HttpClient 实例
        HttpClient httpClient = new DefaultHttpClient();

        // 发送post请求**************************************************************
        HttpPost post = new HttpPost("http://xiaozhameng.fenqi.mgmt.com/a/login");
        // 设置请求信息
        post.setEntity(entity);
        post.setConfig(config);
        HttpResponse postResponse = httpClient.execute(post);
        /**
         * HTTP/1.1 302 Found
         * [Server: nginx/1.11.1,
         * Date: Tue, 17 Jan 2017 08:30:50 GMT,
         * Content-Length: 0,
         * Location: http://xiaozhameng.fenqi.mgmt.com/a;
         * JSESSIONID=bca0deda006a4f58a14fb6e1bc81e2cb?login,
         * Connection: keep-alive,
         * Set-Cookie: jeesite.session.id=bca0deda006a4f58a14fb6e1bc81e2cb; Path=/; HttpOnly, Set-Cookie: rememberMe=deleteMe; Path=/; Max-Age=0; Expires=Mon, 16-Jan-2017 08:30:50 GMT] org.apache.http.conn.BasicManagedEntity@39410c85
         */
        logger.debug("POST ######################### response："+postResponse);

        int postCode = postResponse.getStatusLine().getStatusCode();
        String postStr = EntityUtils.toString(postResponse.getEntity(),"utf-8");
        logger.info("post:请求：\n 响应码："+postCode+"\n响应内容："+postStr);

        // 获取cookie
        String set_cookie = postResponse.getFirstHeader("Set-Cookie").getValue();
        //打印Cookie值
        logger.info("=========================set_cookie："+set_cookie.substring(0,set_cookie.indexOf(";")));

        // 发送get请求****************************************************************
        HttpGet httpget = new HttpGet("http://xiaozhameng.fenqi.mgmt.com/a/fenqiHouse/fenqiHouse/");

        // 根据获得的Cookie值，设置头信息，然后发送请求，获得内容
        httpget.setHeader("Cookie", set_cookie);
        httpget.setConfig(config);
        HttpResponse getResponse = httpClient.execute(httpget);
        /**
         * HTTP/1.1 200 OK
         * [Server: nginx/1.11.1,
         * Date: Tue, 17 Jan 2017 09:10:18 GMT,
         * Content-Type: text/html;charset=UTF-8,
         * Transfer-Encoding: chunked,
         * Connection: keep-alive,
         * Content-Language: zh-CN] org.apache.http.conn.BasicManagedEntity@6d0984e0
         */
        logger.debug("Get ################################## response："+getResponse);
        // 获取响应码
        int getCode = getResponse.getStatusLine().getStatusCode();
        // 获取相应内容
        HttpEntity entity1 = getResponse.getEntity();
        String getStr = EntityUtils.toString(entity1, "utf-8");
        logger.info("get请求：\n 响应码："+getCode+"\n响应内容："+getStr);
    }
}
