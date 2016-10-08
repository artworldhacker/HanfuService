package com.dexingworld.hanfu.web.controller;

import com.dexingworld.hanfu.middleware.redis.RedisCacheManager;
import com.dexingworld.hanfu.repository.dao.DbConfigMapper;
import com.dexingworld.hanfu.repository.entity.AppConfig;
import com.dexingworld.hanfu.repository.entity.DbConfig;
import com.dexingworld.hanfu.service.AppConfigService;
import com.dexingworld.hanfu.web.response.ResultResponse;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangpeng on 2016/9/29.
 */
@RestController
@RequestMapping("/base")
public class BaseController {

    @Autowired
    private RedisCacheManager cacheManager;

    @Autowired
    private AppConfigService appConfigService;

    @Autowired
    private DbConfigMapper dbConfigDao;

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

    @RequestMapping("/getAppCofig")
    public ResultResponse getAppConfig(){
        List<AppConfig> appConfigs = appConfigService.query(new AppConfig());
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResult(appConfigs);
        return resultResponse.makeSuccessful();
    }



    @RequestMapping("/getDbConfig/{id}")
    public ResultResponse getDbConfig(@PathVariable(value = "id")Long id){
        DbConfig dbConfig = dbConfigDao.selectByPrimaryKey(id);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResult(dbConfig);
        return resultResponse.makeSuccessful();
    }
}
