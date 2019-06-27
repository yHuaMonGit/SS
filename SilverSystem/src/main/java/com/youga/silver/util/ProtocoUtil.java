package com.youga.silver.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class ProtocoUtil {


    /**
     * 发送HttpPost请求
     *
     * @param strURL
     *            服务地址
     * @param params
     *
     * @return 成功:返回json字符串<br/>
     */
    public static JSONObject jsonPost(String strURL, Map<String, Object> params) throws IOException {

            return null;
    }


    public static String  postWithParamsForString(String url, List<NameValuePair> params) throws UnsupportedEncodingException {

        HttpClient client = HttpClients.createDefault();
        HttpPost httpPost =   new HttpPost(url);


        String s = "";
        try {

            httpPost.setEntity(new UrlEncodedFormEntity(params,Consts.UTF_8));
            //httpPost.setEntity(check);
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            HttpResponse response = client.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode==200){
                HttpEntity entity = response.getEntity();
                s = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }



}
