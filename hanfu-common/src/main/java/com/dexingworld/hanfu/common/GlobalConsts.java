package com.dexingworld.hanfu.common;

/**
 * 全局常量
 * Created by wangpeng on 2016/10/14.
 */
public class GlobalConsts {

    public static final String WEIXIN_TOKEN_PREFIX = "WEIXIN_TOKEN_PREFIX";

    public static final String UNDER_LINE = "_";

    public static final String CLIENT_CREDENTIAL = "client_credential";
    public static final Long MAX_EXPIRE_TIME = Long.valueOf(Long.parseLong(String.valueOf(2147483647)) * 1000L);
    /**
     * 默认session超时时间
     */
    public static final Long DEFAULT_SESSION_TIMEOUT = Long.valueOf(1800000L);

    public static final String COOKIE_PREFIX = "hanfu-cookies_";

}
