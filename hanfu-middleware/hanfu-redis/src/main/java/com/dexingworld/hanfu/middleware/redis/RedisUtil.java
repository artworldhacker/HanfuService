//package com.dexingworld.hanfu.middleware.redis;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.stereotype.Component;
//
//import java.io.Serializable;
//import java.util.Set;
//
///**
// * Created by wangpeng on 2016/9/30.
// */
//@Component
//public class RedisUtil {
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    public void remove(final String ... keys){
//        for(String key : keys){
//            remove(key);
//        }
//    }
//
//    public void remove(String key){
//        if(exists(key)){
//            redisTemplate.delete(key);
//        }
//    }
//
//    /**
//     * 批量删除key
//     *
//     * @param pattern
//     */
//    public void removePattern(final String pattern) {
//        Set<Serializable> keys = redisTemplate.keys(pattern);
//        if (keys.size() > 0){
//            redisTemplate.delete(keys);
//        }
//    }
//
//    /**
//     * 判断缓存中是否有对应的value
//     *
//     * @param key
//     * @return
//     */
//    public boolean exists(final String key) {
//        return redisTemplate.hasKey(key);
//    }
//
//    public Object get(final String key){
//        ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
//        if(exists(key)){
//            return operations.get(key);
//        }
//        return null;
//    }
//
//    public boolean put(final String key,Object value){
//        return put(key,value,null);
//    }
//
//    public boolean put(final String key,Object value,Long expierTime){
//        boolean result = false;
//        try {
//            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
//            if(expierTime != null){
//                operations.set(key,value,expierTime);
//            }else {
//                operations.set(key,value);
//            }
//            result = true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//
//
//
//}
