package com.dexingworld.hanfu.web.controller;

import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.middleware.redis.RedisCacheManager;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * Created by wangpeng on 2016/9/29.
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private RedisCacheManager cacheManager;

    @RequestMapping("/hello")
    public String hello(){
        return "hello Hanfu";
    }


    @RequestMapping("/response")
    public ResultResponse resultResponse(){
        ResultResponse resultResponse = new ResultResponse();
        Map<String,Object> map = Maps.newHashMap();
        map.put("test","test");
        map.put("data",new Date());
        resultResponse.setResult(map);
        return resultResponse.makeSuccessful();
    }


    private static final String key = "redis_put_test";

    @RequestMapping("/putToRedis")
    public ResultResponse putToRedis(){
        ResultResponse resultResponse = new ResultResponse();
        Map<String,Object> map = Maps.newHashMap();
        map.put("test","test");
        map.put("data", new Date());
        cacheManager.put(key, map);
        return resultResponse.makeSuccessful();
    }

    @RequestMapping("/getToRedis")
    public ResultResponse getToRedis(){
        ResultResponse resultResponse = new ResultResponse();
        Object data = cacheManager.get(key);
        resultResponse.setResult(data);
        return resultResponse.makeSuccessful();
    }

}
