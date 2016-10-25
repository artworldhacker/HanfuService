package com.dexingworld.hanfu.biz.weixin.impl;

import com.alibaba.fastjson.JSON;
import com.dexingworld.hanfu.biz.weixin.WeixinUserService;
import com.dexingworld.hanfu.common.enums.LangEnum;
import com.dexingworld.hanfu.common.enums.WeixinUrlEnum;
import com.dexingworld.hanfu.common.parameter.weixin.user.WeixinUserGetRequest;
import com.dexingworld.hanfu.common.response.weixin.user.OpenId;
import com.dexingworld.hanfu.common.response.weixin.user.WeixinUserGetResponse;
import com.dexingworld.hanfu.common.response.weixin.user.WeixinUserInfo;
import com.dexingworld.hanfu.crawler.HttpUrlUtils;
import com.dexingworld.hanfu.crawler.urlconnection.HttpConnectionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangpeng on 2016/10/14.
 */
@Service
public class WeixinUserServiceImpl implements WeixinUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeixinUserServiceImpl.class);

    @Override
    public WeixinUserGetResponse getWeixinUserList(WeixinUserGetRequest request) {
        Map<String,String> map = Maps.newHashMap();
        String token = request.getAccess_token();
        map.put("access_token",token);
        if(StringUtils.isNoneBlank(request.getNext_openid())){
            map.put("next_openid",request.getNext_openid());
        }
        String url = HttpUrlUtils.attachHttpGetParams(WeixinUrlEnum.USER_GET.getUrl(), map);
        String result = HttpConnectionUtils.get(url);
        WeixinUserGetResponse weixinUserGetResponse = JSON.parseObject(result,WeixinUserGetResponse.class);
        if(weixinUserGetResponse == null){
            LOGGER.error("获取用户列表失败!");
            return null;
        }
        OpenId openId = weixinUserGetResponse.getData();
        if(openId != null){
            List<String> openIdList = openId.getOpenid();
            List<WeixinUserInfo> userInfoList = Lists.newArrayList();
            if(openIdList != null && openIdList.size() > 0){
                for(String openid : openIdList){
                    userInfoList.add(getUserInfo(openid,token));
                }
            }
            weixinUserGetResponse.setUserInfoList(userInfoList);
        }
        return weixinUserGetResponse;
    }

    private WeixinUserInfo getUserInfo(String openid,String token){
        Map<String,String> params = Maps.newHashMap();
        params.put("access_token",token);
        params.put("openid",openid);
        params.put("lang", LangEnum.ZH_CN.getValue());
        String url = HttpUrlUtils.attachHttpGetParams(WeixinUrlEnum.USER_INFO.getUrl(), params);
        String result = HttpConnectionUtils.get(url);
        WeixinUserInfo weixinUserInfo = JSON.parseObject(result,WeixinUserInfo.class);
        return weixinUserInfo;
    }

}
