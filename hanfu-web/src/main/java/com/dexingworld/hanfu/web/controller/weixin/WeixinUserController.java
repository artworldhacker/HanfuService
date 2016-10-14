package com.dexingworld.hanfu.web.controller.weixin;

import com.dexingworld.hanfu.biz.weixin.WeixinService;
import com.dexingworld.hanfu.biz.weixin.WeixinUserService;
import com.dexingworld.hanfu.common.annotation.ProcessException;
import com.dexingworld.hanfu.common.parameter.weixin.WeixinConfig;
import com.dexingworld.hanfu.common.parameter.weixin.user.WeixinUserGetRequest;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.common.response.weixin.TokenResponse;
import com.dexingworld.hanfu.common.response.weixin.user.WeixinUserGetResponse;
import com.dexingworld.hanfu.weixin.util.GetWeixinConfigUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangpeng on 2016/10/14.
 */
@RestController
public class WeixinUserController {

    @Autowired
    private WeixinUserService weixinUserService;

    @Autowired
    private WeixinService weixinService;


    @RequestMapping("/weixin/getUserList")
    @ProcessException
    public ResultResponse getUserList(WeixinConfig weixinConfig){
        ResultResponse result = new ResultResponse();
        weixinConfig = GetWeixinConfigUtils.getWeixinConfig(weixinConfig);
        TokenResponse tokenResponse = weixinService.accessToken(weixinConfig);
        WeixinUserGetRequest request = new WeixinUserGetRequest();
        request.setAccess_token(tokenResponse.getAccess_token());
        WeixinUserGetResponse response = weixinUserService.getWeixinUserList(request);
        result.setResult(response);
        if(tokenResponse.getErrcode() != null){
            return result.makeFailure("请求微信获取用户列表失败!错误代码{"+tokenResponse.getErrcode()+"}，错误消息{"+tokenResponse.getErrmsg()+"}");
        }
        return result.makeSuccessful();
    }

}
