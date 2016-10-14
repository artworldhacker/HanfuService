package com.dexingworld.hanfu.biz.weixin.impl;

import com.alibaba.fastjson.JSON;
import com.dexingworld.hanfu.biz.weixin.WeixinUserService;
import com.dexingworld.hanfu.common.enums.WeixinUrlEnum;
import com.dexingworld.hanfu.common.parameter.weixin.user.WeixinUserGetRequest;
import com.dexingworld.hanfu.common.response.weixin.user.WeixinUserGetResponse;
import com.dexingworld.hanfu.utils.http.HttpUrlUtils;
import com.dexingworld.hanfu.utils.http.urlconnection.HttpConnectionUtils;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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
        map.put("access_token",request.getAccess_token());
        if(StringUtils.isNoneBlank(request.getNext_openid())){
            map.put("next_openid",request.getNext_openid());
        }
        String url = HttpUrlUtils.attachHttpGetParams(WeixinUrlEnum.USER_GET.getUrl(),map);
        String result = HttpConnectionUtils.get(url);
        WeixinUserGetResponse weixinUserGetResponse = JSON.parseObject(result,WeixinUserGetResponse.class);
        if(weixinUserGetResponse == null){
            LOGGER.error("获取用户列表失败!");
            return null;
        }
        return weixinUserGetResponse;
    }

}
