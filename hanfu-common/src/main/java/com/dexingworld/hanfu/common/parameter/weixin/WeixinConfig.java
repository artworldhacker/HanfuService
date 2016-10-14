package com.dexingworld.hanfu.common.parameter.weixin;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/10/14.
 */
public class WeixinConfig implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * appId
     */
    private String appId;

    /**
     * appSecret
     */
    private String secret;


    /**
     * grant_type
     */
    private String grant_type;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }
}
