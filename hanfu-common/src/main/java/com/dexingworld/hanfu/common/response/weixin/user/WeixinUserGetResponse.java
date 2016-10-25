package com.dexingworld.hanfu.common.response.weixin.user;

import com.alibaba.fastjson.JSON;
import com.dexingworld.hanfu.common.response.weixin.WeixinCommonResponse;

import java.util.List;

/**
 * Created by wangpeng on 2016/10/14.
 */
public class WeixinUserGetResponse extends WeixinCommonResponse {
    private static final long serialVersionUID = 1L;

    /**
     * 	关注该公众账号的总用户数
     */
    private String total;

    /**
     * 拉取的OPENID个数，最大值为10000
     */
    private String count;

    /**
     * 列表数据，OPENID的列表
     */
    private OpenId data;

    /**
     * 拉取列表的最后一个用户的OPENID
     */
    private String next_openid;

    private List<WeixinUserInfo> userInfoList;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public OpenId getData() {
        return data;
    }

    public void setData(OpenId data) {
        this.data = data;
    }

    public String getNext_openid() {
        return next_openid;
    }

    public void setNext_openid(String next_openid) {
        this.next_openid = next_openid;
    }

    public List<WeixinUserInfo> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<WeixinUserInfo> userInfoList) {
        this.userInfoList = userInfoList;
    }
}
