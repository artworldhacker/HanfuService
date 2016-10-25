package com.dexingworld.hanfu.crawler;

import com.google.common.collect.Lists;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;

/**
 * 组装Url
 * Created by wangpeng on 2016/10/14.
 */
public class HttpUrlUtils {

    private static final String CHARSET_NAME = "UTF-8";


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

    public static String attachHttpGetParams(String url,Map<String,String> map){
        List<BasicNameValuePair> params = Lists.newArrayList();
        if(map != null){
            for (Map.Entry<String,String> entry : map.entrySet()){
                params.add(new BasicNameValuePair(entry.getKey(),entry.getValue()));
            }
        }
        return attachHttpGetParams(url,params);
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
}
