package com.dexingworld.hanfu.web.controller;

import com.dexingworld.hanfu.web.response.ResultResponse;
import com.google.common.collect.Maps;
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
}
