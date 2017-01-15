package com.dexingworld.hanfu.utils;

import com.google.gson.Gson;

/**
 * Gson转换Json工具类
 * Created by wangpeng on 2016/12/15.
 */
public class GsonUtils {

    private static Gson gson = new Gson();

    public static Gson getInstance(){return gson;}

    public static String writeValueAsString(Object obj){
        return getInstance().toJson(obj);
    }

    public static <T> T readValue(String jsonStr,Class<T> clazz){
        return getInstance().fromJson(jsonStr,clazz);
    }

}
