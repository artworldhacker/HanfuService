package com.dexingworld.hanfu.common.enums;

/**
 * Created by wangpeng on 2016/11/4.
 */
public enum LoginMsgEnum {

    USER_NOUT_FOUNT("10001","用户不存在"),
    USER_PASSWORD_WRONG("10002","用户密码错误"),
    USER_BE_LOCK("10003","用户已锁定"),
    ;


    private String value;

    private String message;

    LoginMsgEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
