package com.dexingworld.hanfu.web.controller;

import com.dexingworld.hanfu.biz.MenuBizService;
import com.dexingworld.hanfu.common.parameter.GetSessionUserParameter;
import com.dexingworld.hanfu.common.parameter.QueryMenuParameter;
import com.dexingworld.hanfu.common.response.MenuView;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.session.SessionProcess;
import com.dexingworld.hanfu.web.annotation.NeedLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangpeng on 2016/11/4.
 */

/**
 * 导航controller
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private MenuBizService menuBizService;

    @Autowired
    private SessionProcess sessionProcess;


    /**
     * 查询当前登陆用户所拥有的菜单
     * @param queryMenuParameter
     * @return
     */
    @NeedLogin
    @ExceptionHandler
    @RequestMapping("/menu")
    public ResultResponse queryUserMenu(QueryMenuParameter queryMenuParameter){
        return menuBizService.queryUserMenu(queryMenuParameter);
    }

    @NeedLogin
    @ExceptionHandler
    @RequestMapping("/getSessionUser")
    public ResultResponse getSessionUser(GetSessionUserParameter sessionUserParameter){
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResult(sessionUserParameter.getSessionUser());
        return resultResponse.makeSuccessful();
    }

    @NeedLogin
    @ExceptionHandler
    @RequestMapping("/logout")
    public ResultResponse logout(HttpServletRequest request){
        sessionProcess.logout(request);
        return new ResultResponse(true);
    }
}
