package com.dexingworld.hanfu.biz.weixin;

import com.dexingworld.hanfu.common.parameter.weixin.WeixinConfig;
import com.dexingworld.hanfu.common.response.weixin.TokenResponse;

/**
 * Created by wangpeng on 2016/10/14.
 */
public interface WeixinService {

    TokenResponse accessToken(WeixinConfig weixinConfig);
}
