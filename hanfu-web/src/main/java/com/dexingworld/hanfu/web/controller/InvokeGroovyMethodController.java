package com.dexingworld.hanfu.web.controller;

import com.dexingworld.hanfu.common.parameter.GroovyRequest;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.middleware.component.GroovyComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by wangpeng on 2016/10/19.
 */
@RestController
@RequestMapping("/groovy")
public class InvokeGroovyMethodController {

    public static final Logger LOG = LoggerFactory.getLogger(InvokeGroovyMethodController.class);

    @Autowired
    private GroovyComponent groovyComponent;

    @RequestMapping("/invoke")
    public ResultResponse invokeMethod(@Valid GroovyRequest request,BindingResult bindingResult){
        ResultResponse resultResponse = new ResultResponse();
        if(bindingResult.hasErrors()){
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            return resultResponse.makeFailure(errorMsg);
        }
        if(!GroovyComponent.isInit){
            groovyComponent.init();
        }
        resultResponse =  groovyComponent.invokeGroovyMethod(request.getCode(),request.getMethod(),request.getMap());
        return resultResponse;
    }

}
