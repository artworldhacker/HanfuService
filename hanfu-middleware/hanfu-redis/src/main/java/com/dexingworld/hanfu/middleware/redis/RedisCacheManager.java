package com.dexingworld.hanfu.middleware.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangpeng on 2016/9/30.
 */
@Component
public class RedisCacheManager {

    @Autowired
    private RedisCache redisCache;

    public void put(Object key,Object value){
        redisCache.add(key,value);
    }

    public void put(Object key,Object value,Long expireTime){
        redisCache.add(key,value,expireTime);
    }

    public Object get(Object key){
        if(isExist(key)){
            return redisCache.get(key);
        }
        return false;
    }

    public String getString(Object key){
        Object value = get(key);
        return value != null ? (String)value : null;
    }

    public Integer getInt(Object key){
        Object value = get(key);
        return value != null ? (Integer) key : null;
    }

    public Long getLong(Object key){
        Object value = get(key);
        return value != null ? (Long) key : null;
    }

    public boolean isExist(Object key){
        if(redisCache.hasKey(key)){
            return true;
        }
        return false;
    }


}
