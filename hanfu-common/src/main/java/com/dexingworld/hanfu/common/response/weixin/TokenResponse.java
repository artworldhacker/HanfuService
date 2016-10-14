package com.dexingworld.hanfu.common.response.weixin;

/**
 * Created by wangpeng on 2016/10/14.
 */
public class TokenResponse extends WeixinCommonResponse{
    private static final long serialVersionUID = 1L;
    private String access_token;

    private Long expires_in;



    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

}
