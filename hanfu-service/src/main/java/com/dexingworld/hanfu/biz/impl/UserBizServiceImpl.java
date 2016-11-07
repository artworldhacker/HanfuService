package com.dexingworld.hanfu.biz.impl;

import com.dexingworld.hanfu.biz.UserBizService;
import com.dexingworld.hanfu.common.GlobalConsts;
import com.dexingworld.hanfu.common.enums.LoginMsgEnum;
import com.dexingworld.hanfu.common.enums.LoginStatusEnum;
import com.dexingworld.hanfu.common.parameter.AddUser;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.repository.entity.User;
import com.dexingworld.hanfu.service.UserService;
import com.dexingworld.hanfu.session.SessionProcess;
import com.dexingworld.hanfu.utils.Base64Util;
import com.dexingworld.hanfu.utils.PropertieUtils;
import com.dexingworld.hanfu.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by wangpeng on 2016/11/4.
 */
@Service
public class UserBizServiceImpl implements UserBizService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserBizServiceImpl.class);

    private static final String PASSWORD_DEFAULT = "password.default";

    @Autowired
    private UserService userService;

    @Autowired
    private SessionProcess sessionProcess;


    @Override
    @Transactional(readOnly = true)
    public ResultResponse login(String userName, String passWord) {
        ResultResponse resultResponse = new ResultResponse();
        User user = new User();
        user.setUserName(userName);
        user = userService.get(user);
        if (user == null) {
            LOGGER.error("登陆失败，用户{}不存在", userName);
            return resultResponse.makeFailure(LoginMsgEnum.USER_NOUT_FOUNT.getMessage());
        }
        String encodePassword = Base64Util.encodeHanfuPassWord(passWord);
        if (!encodePassword.equals(user.getPassWord())) {
            LOGGER.error("登陆失败，用户{}密码错误", userName);
            return resultResponse.makeFailure(LoginMsgEnum.USER_PASSWORD_WRONG.getMessage());
        }
        if (LoginStatusEnum.LOCK.isEqual(user.getStatus())) {
            LOGGER.error("登陆失败，用户{}已锁定", userName);
            return resultResponse.makeFailure(LoginMsgEnum.USER_BE_LOCK.getMessage());
        }
        LOGGER.info("用户{}登陆成功", userName);
        resultResponse.setResult(user);
        resultResponse.setStatus(true);
        return resultResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public ResultResponse login(String userName, String passWord, HttpServletResponse response) {
        ResultResponse resultResponse =  login(userName, passWord);
        if (!resultResponse.isStatus()) {
            return resultResponse;
        }
        //存入cookie
        String accessToken = sessionProcess.login((User) resultResponse.getResult(), response, GlobalConsts.DEFAULT_SESSION_TIMEOUT);
        if (StringUtils.isEmpty(accessToken)) {
            LOGGER.error("存入cookie出错！！！！");
        }
        //记录token
        return resultResponse.makeSuccessful();
    }


    @Override
    @Transactional
    public ResultResponse addUser(AddUser addUser) {
        ResultResponse resultResponse = new ResultResponse();
        try {
            User user = new User();
            BeanUtils.copyProperties(addUser, user);
            Date now = new Date();
            user.setCreateTime(now);
            user.setUpdateTime(now);
            user.setStatus(LoginStatusEnum.NORMAL.getCode());
            user.setPassWord(Base64Util.encodeHanfuPassWord(PropertieUtils.getString(PASSWORD_DEFAULT)));//初始化密码
            userService.add(user);
        } catch (Exception e) {
            return resultResponse.makeFailure(e.getMessage());
        }
        return resultResponse.makeSuccessful();
    }
}
