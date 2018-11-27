package com.xiaozhameng.httpclient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by xiaozhameng on 2017/1/17.
 * httpClient 测试类：这种方式是用了一个http的连接池，同时使用httpbuilder构造合适的http方法。
 */
public class HttpClient02 {

    private static PoolingHttpClientConnectionManager manager = null;
    private static HttpClientBuilder builder = null;
    private static RequestConfig config = null;

    private static int MAXCONNECTION = 10;
    private static int DEFAULTMAXCONNECTION = 5;
    private static String IP = "cnivi.com.cn";
    private static int PORT = 801;

    // 初始化
    static {
        //设置http的状态参数
        config = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        HttpHost httpHost = new HttpHost(IP,PORT);
        manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(MAXCONNECTION);
        manager.setDefaultMaxPerRoute(DEFAULTMAXCONNECTION);
        manager.setMaxPerRoute(new HttpRoute(httpHost),20);

        builder = HttpClients.custom();
        builder.setConnectionManager(manager);
    }

    public static CloseableHttpClient getConnection() {
        CloseableHttpClient httpClient = builder.build();
        httpClient = builder.build();
        return httpClient;
    }

    public static HttpUriRequest getRequestMethod(Map<String, String> map, String url, String method) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        for (Map.Entry<String, String> e : entrySet) {
            String name = e.getKey();
            String value = e.getValue();
            NameValuePair pair = new BasicNameValuePair(name, value);
            params.add(pair);
        }
        HttpUriRequest reqMethod = null;
        if ("post".equals(method)) {
            reqMethod = RequestBuilder.post().setUri(url)
                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
                    .setConfig(config).build();
        } else if ("get".equals(method)) {
            reqMethod = RequestBuilder.get().setUri(url)
                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
                    .setConfig(config).build();
        }
        return reqMethod;
    }

    public static void main(String args[]) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("account", "");
        map.put("password", "");

        HttpClient client = getConnection();
        HttpUriRequest post = getRequestMethod(map, "http://www.baidu.com", "get");
        HttpResponse response = client.execute(post);

        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            String message = EntityUtils.toString(entity, "utf-8");
            System.out.println(message);
        } else {
            System.out.println("请求失败");
        }
    }
}
