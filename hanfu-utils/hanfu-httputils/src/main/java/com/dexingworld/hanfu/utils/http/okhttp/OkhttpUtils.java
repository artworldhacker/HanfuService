package com.dexingworld.hanfu.utils.http.okhttp;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.squareup.okhttp.*;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangpeng on 2016/10/13.
 */
public class OkhttpUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(OkhttpUtils.class);

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    private static final String CHARSET_NAME = "UTF-8";

    private static final OkHttpClient okHttpClient = new OkHttpClient();

    static {
        okHttpClient.setConnectTimeout(30, TimeUnit.SECONDS);
    }


    public static Response excute(Request request){
        try {
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(),e);
            return null;
        }
    }


    /**
     * 开启异步线程访问网络
     * @param request
     * @param responseCallback
     */
    public static void enqueue(Request request, Callback responseCallback){
        okHttpClient.newCall(request).enqueue(responseCallback);
    }

    /**
     * 开启异步线程访问网络, 且不在意返回结果（实现空callback）
     * @param request
     */
    public static void enqueue(Request request){
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(Response arg0) throws IOException {

            }
            @Override
            public void onFailure(Request arg0, IOException arg1) {

            }
        });
    }

    public static String get(String url,Map<String,String> map){
        List<BasicNameValuePair> list = Lists.newArrayList();
        if(map != null){
            for (Map.Entry<String,String> entry : map.entrySet()){
                list.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }
        return get(url,list);
    }

    public static String get(String url,List<BasicNameValuePair> list){
        return get(attachHttpGetParams(url,list));
    }

    public static String get(String url){
        Request request = new Request.Builder().url(url).build();
        Response response = excute(request);
        return transResponse(response);
    }

    /**
     * 这里使用了HttpClinet的API。只是为了方便
     * @param params
     * @return
     */
    public static String formatParams(List<BasicNameValuePair> params){
        return URLEncodedUtils.format(params, CHARSET_NAME);
    }

    /**
     * 为HttpGet 的 url 方便的添加多个name value 参数。
     * @param url
     * @param params
     * @return
     */
    public static String attachHttpGetParams(String url, List<BasicNameValuePair> params){
        return url + "?" + formatParams(params);
    }

    /**
     * 为HttpGet 的 url 方便的添加1个name value 参数。
     * @param url
     * @param name
     * @param value
     * @return
     */
    public static String attachHttpGetParam(String url, String name, String value){
        return url + "?" + name + "=" + value;
    }

    public static String postBody(String url,String postBody){
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, postBody))
                .build();
        Response response = excute(request);
        return transResponse(response);
    }

    private static String transResponse(Response response){
        if(response == null){
            return null;
        }
        try {
            if(response.isSuccessful()){
                return response.body().string();
            }else {
                LOGGER.error("Unexpected code " + response);
                return null;
            }
        } catch (IOException e) {
            LOGGER.error("Unexpected");
            return null;
        }
    }

    public static String postFile(String url,String path){
        return postFile(url,new File(path));
    }

    public static String postFile(String url,File file){
        Request request = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                .build();
        Response response = excute(request);
        return transResponse(response);
    }

    public static String postForm(String url,Map<String,String> map){
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        if(map != null){
            for(Map.Entry<String,String> entry : map.entrySet()){
                formEncodingBuilder.add(entry.getKey(),entry.getValue());
            }
        }
        RequestBody requestBody = formEncodingBuilder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = excute(request);
        return transResponse(response);
    }


    public static void main(String[] args) {
        /*String appId = "";
        String secret_id = "";
        String grant_type = "client_credential";
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        List<BasicNameValuePair> list = Lists.newArrayList();
        list.add(new BasicNameValuePair("grant_type",grant_type));
        list.add(new BasicNameValuePair("appid",appId));
        list.add(new BasicNameValuePair("secret",secret_id));
        String result = OkhttpUtils.get(url,list);
        JSONObject json = JSONObject.parseObject(result);
        String errcode = json.getString("errcode");
        if(StringUtils.isEmpty(errcode)){
            String access_token = json.getString("access_token");
            System.out.println(access_token);
        }else {
            String errorMsg = json.getString("errmsg");
            System.out.println("错误消息【"+errorMsg+"】,错误代码【"+errcode+"】");
        }*/
        String url = "https://api.weixin.qq.com/cgi-bin/user/get";
        Map<String,String> map = Maps.newHashMap();
        map.put("access_token","e8jkZ480OYdkEOZU0JhLOHEpXn87B3f_GFHu_Bq_hl-sDk7s9srJnGUoI_DM-piuOzrCFxOiAeaXCX2MU6_mPLcEpB2EEWn3vx70L7IIw0QYQvQFB39hVx-ZjYX2PV43FVFiAHAXNX");
        String result = OkhttpUtils.get(url,map);
        System.out.println(result);
    }










}
