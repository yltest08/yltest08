package org.example.test;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class T3 {
    public static void main(String[] args) throws IOException {
        CookieStore cookieStore = new BasicCookieStore();

        // Create a CloseableHttpClient with the cookie store
        CloseableHttpClient client = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        HttpGet httpGet = new HttpGet("http://127.0.0.1:3000/get227");
        // Execute the request and get the response
        CloseableHttpResponse httpResponse = client.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        String htmlContent = entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
        System.out.println(htmlContent);
    }
}