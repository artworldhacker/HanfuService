package com.dexingworld.hanfu.biz;

import com.dexingworld.hanfu.common.parameter.AddUser;
import com.dexingworld.hanfu.common.response.ResultResponse;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangpeng on 2016/11/4.
 */
public interface UserBizService {


    ResultResponse login(String userName,String passWord);
    ResultResponse login(String userName,String passWord,HttpServletResponse response);

    ResultResponse addUser(AddUser addUser);
}
