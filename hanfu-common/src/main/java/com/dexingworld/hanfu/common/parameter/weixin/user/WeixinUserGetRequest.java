package com.dexingworld.hanfu.common.parameter.weixin.user;

import com.dexingworld.hanfu.common.parameter.weixin.WeixinBaseRequest;

/**
 * Created by wangpeng on 2016/10/14.
 */
public class WeixinUserGetRequest extends WeixinBaseRequest {

    private static final long serialVersionUID = 1L;

    /**
     * 	第一个拉取的OPENID，不填默认从头开始拉取
     */
    private String next_openid;

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }
}
