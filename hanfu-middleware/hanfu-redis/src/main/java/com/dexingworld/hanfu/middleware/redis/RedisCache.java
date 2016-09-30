package com.dexingworld.hanfu.middleware.redis;

import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
* Created by wangpeng on 2016/9/30.
*/
@Component
public class RedisCache {

    @Resource(name = "redisTemplate")
    private RedisOperations<Object, Object> redisTemplate;

    public void add(Object key, Object value, Long expireIn) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
        redisTemplate.expire(key, expireIn, TimeUnit.MILLISECONDS);
    }


    public void add(Object key, Object value) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        ops.set(key, value);
    }

    public boolean hasKey(Object key) {
        if (redisTemplate.hasKey(key)) {
            return true;
        }
        return false;
    }

    public Object get(Object key) {
        ValueOperations<Object, Object> ops = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            return ops.get(key);
        }
        return null;
    }

    public void delete(Object key) {
        redisTemplate.delete(key);
    }

    public void deleteAll(Collection<Object> key) {
        redisTemplate.delete(key);
    }
}
