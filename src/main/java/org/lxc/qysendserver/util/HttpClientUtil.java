package org.lxc.qysendserver.util;

import com.alibaba.fastjson2.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HttpClientUtil {

    private static final int CONNECT_TIMEOUT = 5000; // 连接超时时间(毫秒)
    private static final int SOCKET_TIMEOUT = 5000;  // 读取超时时间(毫秒)

    private static CloseableHttpClient getHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setSocketTimeout(SOCKET_TIMEOUT)
                .build();

        return HttpClientBuilder.create()
                .setDefaultRequestConfig(config)
                .build();
    }

    /**
     * 发送GET请求
     * @param url 请求URL
     * @param params 请求参数
     * @param headers 请求头
     * @return 响应内容
     * @throws IOException
     */
    public String get(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        // 构建带参数的URL
        StringBuilder urlBuilder = new StringBuilder(url);
        if (params != null && !params.isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                urlBuilder.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&");
            }
            urlBuilder.deleteCharAt(urlBuilder.length() - 1); // 删除最后一个"&"
        }

        HttpGet httpGet = new HttpGet(urlBuilder.toString());

        // 设置请求头
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpGet.setHeader(entry.getKey(), entry.getValue());
            }
        }

        try (CloseableHttpClient httpClient = getHttpClient()) {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            return null;
        }
    }

    /**
     * 发送POST请求(表单)
     * @param url 请求URL
     * @param params 表单参数
     * @param headers 请求头
     * @return 响应内容
     * @throws IOException
     */
    public String postForm(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        }

        // 设置表单参数
        if (params != null && !params.isEmpty()) {
            List<NameValuePair> formParams = new ArrayList<>();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
            httpPost.setEntity(new UrlEncodedFormEntity(formParams, "UTF-8"));
        }

        try (CloseableHttpClient httpClient = getHttpClient()) {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            return null;
        }
    }

    /**
     * 发送POST请求(JSON)
     * @param url 请求URL
     * @param jsonBody JSON请求体
     * @param headers 请求头
     * @return 响应内容
     * @throws IOException
     */
    public String postJson(String url, JSONObject jsonBody, Map<String, String> headers) throws IOException {
        HttpPost httpPost = new HttpPost(url);

        // 设置请求头
        if (headers != null) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpPost.setHeader(entry.getKey(), entry.getValue());
            }
        } else {
            httpPost.setHeader("Content-Type", "application/json");
        }

        // 设置JSON请求体
        if (jsonBody != null && !jsonBody.isEmpty()) {
            HttpEntity jsonEntity = new StringEntity(jsonBody.toString(), "UTF-8");
            httpPost.setEntity(jsonEntity);
        }

        try (CloseableHttpClient httpClient = getHttpClient()) {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity);
            }
            return null;
        }
    }
}