package com.dexingworld.hanfu.common.response.weixin;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/10/14.
 */
public class WeixinCommonResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer errcode;

    private String errmsg;

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }
}
