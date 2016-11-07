package com.dexingworld.hanfu.common.enums;

/**
 * Created by wangpeng on 2016/11/7.
 */
public enum SysCodeEnum {

    NO_LOGIN("10001","用户未登陆",false),
    LOGIN_SUCCESS("10000","登陆成功",true);
    ;

    private String code;
    private String message;
    private boolean success;

    SysCodeEnum(String code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
