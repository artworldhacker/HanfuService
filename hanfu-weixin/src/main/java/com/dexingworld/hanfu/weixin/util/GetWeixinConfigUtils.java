package com.dexingworld.hanfu.weixin.util;

import com.dexingworld.hanfu.common.parameter.weixin.WeixinConfig;
import com.dexingworld.hanfu.utils.PropertieUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by wangpeng on 2016/10/14.
 */
public class GetWeixinConfigUtils {


    public static WeixinConfig getWeixinConfig(WeixinConfig weixinConfig){
        if(weixinConfig == null){
            weixinConfig = new WeixinConfig();
        }
        if(StringUtils.isEmpty(weixinConfig.getAppId())){
            String appId = PropertieUtils.getString("weixin.app.id");
            weixinConfig.setAppId(appId);
        }
        if(StringUtils.isEmpty(weixinConfig.getSecret())){
            String secret = PropertieUtils.getString("weixin.app.secret.key");
            weixinConfig.setSecret(secret);
        }
        return weixinConfig;
    }


    public static WeixinConfig getWeixinConfig(){
        WeixinConfig weixinConfig = new WeixinConfig();
        String appId = PropertieUtils.getString("weixin.app.id");
        String secret = PropertieUtils.getString("weixin.app.secret.key");
        weixinConfig.setAppId(appId);
        weixinConfig.setSecret(secret);
        return weixinConfig;
    }
}
