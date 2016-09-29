package com.dexingworld.hanfu.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
