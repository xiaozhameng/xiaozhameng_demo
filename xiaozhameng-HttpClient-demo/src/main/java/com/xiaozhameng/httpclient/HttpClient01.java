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
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * created by xiaozhameng on 2017-01-17
 * httpclient 测试类
 */
public class HttpClient01 {

    /**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 测试方法
     */
    @Test
    public void testHttpClient1() throws IOException{
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
        HttpPost post = new HttpPost("http://xiaozhameng.fenqi.mgmt.com");
        // 设置请求信息
        post.setEntity(entity);
        post.setConfig(config);
        HttpResponse postResponse = httpClient.execute(post);
        int postCode = postResponse.getStatusLine().getStatusCode();
        String postStr = EntityUtils.toString(postResponse.getEntity(),"utf-8");
        logger.info("post:请求：\n 响应码："+postCode+"\n响应内容："+postStr);


        // 发送get请求****************************************************************
        HttpGet get = new HttpGet("http://xiaozhameng.fenqi.mgmt.com/a/fenqiApply/fenqiApply");
        get.setConfig(config);
        HttpResponse getResponse = httpClient.execute(get);
        // 获取响应码
        int getCode = getResponse.getStatusLine().getStatusCode();
        // 获取相应内容
        HttpEntity entity1 = getResponse.getEntity();
        String getStr = EntityUtils.toString(entity1, "utf-8");
        logger.info("get请求：\n 响应码："+getCode+"\n响应内容："+getStr);
    }
}
