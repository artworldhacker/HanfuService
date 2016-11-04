package com.dexingworld.hanfu.common.enums;

/**
 * Created by wangpeng on 2016/11/4.
 */
public enum LoginStatusEnum {
    NORMAL("0","正常"),
    LOCK("1","锁定"),
    ;


    private String code;

    private String message;

    LoginStatusEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEqual(String text){
        return code.equals(text);
    }
}
