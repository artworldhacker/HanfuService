package com.dexingworld.hanfu.common.response.weixin.user;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangpeng on 2016/10/14.
 */
public class OpenId implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<String> openid;

    public List<String> getOpenid() {
        return openid;
    }

    public void setOpenid(List<String> openid) {
        this.openid = openid;
    }


}
