package com.dexingworld.hanfu.common.enums;

/**
 * Created by wangpeng on 2016/10/24.
 */
public enum  LangEnum {
    ZH_CN("zh_CN","简体"),
    ZH_TW("zh_TW","繁体"),
    EN("en","英文"),
    ;



    private String value;
    private String message;

    LangEnum(String value, String message) {
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
