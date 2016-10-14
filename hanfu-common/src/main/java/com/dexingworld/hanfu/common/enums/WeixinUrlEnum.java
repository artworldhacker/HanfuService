package com.dexingworld.hanfu.common.enums;

/**
 * Created by wangpeng on 2016/10/14.
 */
public enum WeixinUrlEnum {

    ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token","获取access token"),
    USER_GET("https://api.weixin.qq.com/cgi-bin/user/get","获取用户列表"),
    ;

    private String url;

    private String message;

    WeixinUrlEnum(String url, String message) {
        this.url = url;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
