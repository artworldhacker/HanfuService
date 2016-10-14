package com.dexingworld.hanfu.biz.weixin;

import com.dexingworld.hanfu.common.parameter.weixin.user.WeixinUserGetRequest;
import com.dexingworld.hanfu.common.response.weixin.user.WeixinUserGetResponse;

/**
 * Created by wangpeng on 2016/10/14.
 */
public interface WeixinUserService {

    WeixinUserGetResponse getWeixinUserList(WeixinUserGetRequest request);
}
