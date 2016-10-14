package com.dexingworld.hanfu.biz.weixin.impl;

import com.alibaba.fastjson.JSON;
import com.dexingworld.hanfu.biz.weixin.WeixinService;
import com.dexingworld.hanfu.common.GlobalConsts;
import com.dexingworld.hanfu.common.enums.WeixinUrlEnum;
import com.dexingworld.hanfu.common.parameter.weixin.WeixinConfig;
import com.dexingworld.hanfu.common.response.weixin.TokenResponse;
import com.dexingworld.hanfu.middleware.redis.RedisCacheManager;
import com.dexingworld.hanfu.utils.http.HttpUrlUtils;
import com.dexingworld.hanfu.utils.http.urlconnection.HttpConnectionUtils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by wangpeng on 2016/10/14.
 */
@Service
public class WeixinServiceImpl implements WeixinService {


    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinServiceImpl.class);

    @Autowired
    private RedisCacheManager cacheManager;


    /**
     * 获取微信token
     * @param weixinConfig
     * @return
     */
    @Override
    public TokenResponse accessToken(WeixinConfig weixinConfig) {
        String tokenKey = processTokenCacheKey(weixinConfig);
        TokenResponse tokenResponse = null;
        try {
            tokenResponse = (TokenResponse) cacheManager.get(tokenKey);
        } catch (Exception e) {
            cacheManager.delete(tokenKey);
        }
        if(tokenResponse != null){
            return tokenResponse;
        }
        //获取token
        Map<String,String> map = Maps.newHashMap();
        map.put("grant_type",GlobalConsts.CLIENT_CREDENTIAL);
        map.put("appid",weixinConfig.getAppId());
        map.put("secret",weixinConfig.getSecret());
        String accessTokenUrl = HttpUrlUtils.attachHttpGetParams( WeixinUrlEnum.ACCESS_TOKEN.getUrl(),map);
        String reponse = HttpConnectionUtils.get(accessTokenUrl);
        if(StringUtils.isEmpty(reponse)){
            LOGGER.error("请求微信获取token失败!");
            return null;
        }
        tokenResponse = JSON.parseObject(reponse,TokenResponse.class);
        if(tokenResponse == null ){
            LOGGER.error("请求微信获取token失败!");
            return null;
        }
        if(tokenResponse.getErrcode() != null){
            LOGGER.error("请求微信获取token失败!错误代码{}，错误消息{}",tokenResponse.getErrcode(),tokenResponse.getErrmsg());
            return tokenResponse;
        }
        //请求成功
        cacheManager.put(tokenKey,tokenResponse,tokenResponse.getExpires_in()*1000);//保存token信息
        return tokenResponse;
    }

    private String processTokenCacheKey(WeixinConfig weixinConfig){
        StringBuffer sb = new StringBuffer();
        sb.append(GlobalConsts.WEIXIN_TOKEN_PREFIX).append(GlobalConsts.UNDER_LINE).append(weixinConfig.getAppId())
        .append(GlobalConsts.UNDER_LINE).append(weixinConfig.getSecret());
        return sb.toString();
    }
}
