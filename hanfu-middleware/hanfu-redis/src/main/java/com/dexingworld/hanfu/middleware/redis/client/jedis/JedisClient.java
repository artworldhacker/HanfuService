package com.dexingworld.hanfu.middleware.redis.client.jedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangpeng on 2016/10/26.
 */
@Component
public class JedisClient {

    @Autowired
    private JedisConfigration jedisConfigration;


    public void add(Object key,Long expireIn,Object object){
        if(expireIn == null){
            jedisConfigration.put(key,object);
        }else {
            jedisConfigration.put(key,expireIn,object);
        }
    }

    public void add(Object key,Object object){
        add(key,null,object);
    }

    public boolean hasKey(Object key){
        return jedisConfigration.exists(key);
    }

    public void del(Object key){
        if(hasKey(key)){
            jedisConfigration.remove(key);
        }
    }


    public Object get(Object key,Class clazz){
        if(!hasKey(key)){
            return null;
        }
        return jedisConfigration.getObject(key,clazz);
    }

    public String getString(Object key){
        return (String)get(key,String.class);
    }

}
