package com.dexingworld.hanfu.common.parameter.weixin;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/10/14.
 */
public class WeixinBaseRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 调用接口凭证
     */
    private String access_token;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
