package com.dexingworld.hanfu.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by wangpeng on 2016/11/4.
 */
public class CookieUtils {

    public static void setCookieHttpOnly(HttpServletResponse response, String cookieKey, String cookieValue, String domain, String path, Integer expiry) {
        setCookie(response, cookieKey, cookieValue, domain, path, expiry, Boolean.valueOf(true));
    }

    public static void setCookie(HttpServletResponse response, String cookieKey, String cookieValue, String domain, String path, Integer expiry, Boolean isHttpOnly) {
        if(null != response && StringUtils.isNotEmpty(cookieKey)) {
            Cookie cookie = new Cookie(cookieKey, cookieValue);
            if(null != domain) {
                cookie.setDomain(domain);
            }

            if(null != path) {
                cookie.setPath(path);
            }

            if(null != expiry) {
                cookie.setMaxAge(expiry.intValue());
            }

            if(null != isHttpOnly) {
                cookie.setHttpOnly(isHttpOnly.booleanValue());
            }

            response.addCookie(cookie);
        }

    }
}
