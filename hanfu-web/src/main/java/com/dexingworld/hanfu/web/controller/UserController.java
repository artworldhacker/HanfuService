package com.dexingworld.hanfu.web.controller;

import com.dexingworld.hanfu.biz.UserBizService;
import com.dexingworld.hanfu.common.parameter.AddUser;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by wangpeng on 2016/11/4.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserBizService userBizService;


    /**
     * 登录
     * @param userName
     * @param passWord
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler
    @RequestMapping("/login")
    public ResultResponse login(@RequestParam(value = "userName") String userName,@RequestParam(value = "passWord") String passWord,ServletRequest request,HttpServletResponse response){
        ResultResponse resultResponse = new ResultResponse();
        if(StringUtils.isEmpty(userName)){
            resultResponse.makeFailure("用户名不能为空！");
        }
        if(StringUtils.isEmpty(passWord)){
            resultResponse.makeFailure("密码不能为空！");
        }
        resultResponse =  userBizService.login(userName,passWord,response);
        return resultResponse;
    }


    /**
     * 用户增加
     * @param addUser
     * @param bindingResult
     * @return
     */
    @ExceptionHandler
    @RequestMapping("/add")
    public ResultResponse add(@Valid AddUser addUser,BindingResult bindingResult){
        ResultResponse resultResponse = new ResultResponse();
        if(bindingResult != null && bindingResult.hasErrors()){
            return resultResponse.makeFailure(bindingResult.getFieldError().getDefaultMessage());
        }
        return userBizService.addUser(addUser);
    }
}
