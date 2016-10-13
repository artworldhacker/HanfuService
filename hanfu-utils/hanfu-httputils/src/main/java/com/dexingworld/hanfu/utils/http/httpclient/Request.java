package com.dexingworld.hanfu.utils.http.httpclient;

import com.google.common.collect.Lists;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangpeng on 2016/7/1.
 * 请求对象
 */
public class Request implements Serializable{

    public static final String PROXY = "proxy";
    public static final String ENTITY_TYPE_STRING = "stringEntity";
    private String url;
    private String method;
    private Map<String,Object> extras;
    private long priority;
    private Map<String,String> params;

    private Map<String,String> headers;

    private String stringEntity;


    public Request clear(){
        this.url = null;
        this.method = null;
        stringEntity = null;
        priority = 0;
        if(extras != null){
            extras.clear();
        }
        if(params != null){
            params.clear();
        }
        if(headers != null){
            headers.clear();
        }
        return this;
    }


    public String getUrl() {
        return url;
    }

    public Request setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getMethod() {
        return method;
    }

    public Request setMethod(String method) {
        this.method = method;
        return this;
    }

    public Map<String, Object> getExtras() {
        return extras;
    }

    public Request setExtras(String key, Object value) {
        if (extras == null) {
            extras = new HashMap<String, Object>();
        }
        extras.put(key, value);
        return this;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public Request setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public List<NameValuePair> getNvps(){
        List<NameValuePair> nvps = Lists.newArrayList();
        if(params != null){
            for(String key : params.keySet()){
                nvps.add(new BasicNameValuePair(key,params.get(key)));
            }
        }
        return nvps;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Request setHeaders(String key,String value) {
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put(key, value);
        return this;
    }

    public String getStringEntity() {
        return stringEntity;
    }

    public Request setStringEntity(String stringEntity) {
        this.stringEntity = stringEntity;
        return this;
    }
}
