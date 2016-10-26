package com.dexingworld.hanfu.middleware.redis.client.jedis;

import com.dexingworld.hanfu.utils.PropertieUtils;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by wangpeng on 2016/10/25.
 */
//@Component
public class JedisConfigration {

    private static ShardedJedisPool shardedJedisPool;

    private static JedisPoolConfig jedisPoolConfig;

    //@PostConstruct
    public void init(){
        if(jedisPoolConfig == null){
            jedisPoolConfig = new JedisPoolConfig();
            int maxTotal = PropertieUtils.getInt("redis.pool.maxTotal");
            int maxIdle = PropertieUtils.getInt("redis.pool.maxIdle");
            int maxWaitMillis = PropertieUtils.getInt("redis.pool.maxWaitMillis");
            boolean testOnBorrow = PropertieUtils.getBoolean("redis.pool.testOnBorrow");
            boolean testOnReturn = PropertieUtils.getBoolean("redis.pool.testOnReturn");
            boolean testWhileIdle = PropertieUtils.getBoolean("redis.pool.testWhileIdle");
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxTotal(maxTotal);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            jedisPoolConfig.setTestOnBorrow(testOnBorrow);
            jedisPoolConfig.setTestOnReturn(testOnReturn);
            jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        }
        if(shardedJedisPool != null){
            List<JedisShardInfo> shardInfoList = Lists.newArrayList();
            String host = PropertieUtils.getString("redis.host");
            int port = PropertieUtils.getInt("redis.port");
            int timeout = PropertieUtils.getInt("redis.timeout");
            String password = PropertieUtils.getString("redis.password");
            JedisShardInfo jedisShardInfo = new JedisShardInfo(host,port,timeout);
            if(StringUtils.isNoneBlank(password)){
                jedisShardInfo.setPassword(password);
            }
            shardInfoList.add(jedisShardInfo);
            shardedJedisPool = new ShardedJedisPool(jedisPoolConfig,shardInfoList);
        }
    }



    private ShardedJedis gerResource(){
        if(shardedJedisPool == null){
            init();
        }
        return shardedJedisPool.getResource();
    }





}
