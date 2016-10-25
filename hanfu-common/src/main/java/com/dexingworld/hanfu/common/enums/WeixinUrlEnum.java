package com.dexingworld.hanfu.common.enums;

/**
 * Created by wangpeng on 2016/10/14.
 */
public enum WeixinUrlEnum {

    ACCESS_TOKEN("https://api.weixin.qq.com/cgi-bin/token","GET","获取access token"),
    USER_GET("https://api.weixin.qq.com/cgi-bin/user/get","GET","获取用户列表"),
    USER_INFO("https://api.weixin.qq.com/cgi-bin/user/info","GET","获取用户基本信息"),
    USER_INFO_BATCH_GET("https://api.weixin.qq.com/cgi-bin/user/info/batchget","POST","批量获取用户基本信息"),
    ;

    private String url;
    private String type;
    private String message;


    WeixinUrlEnum(String url, String type, String message) {
        this.url = url;
        this.type = type;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
