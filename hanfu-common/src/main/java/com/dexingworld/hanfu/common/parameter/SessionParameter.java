package com.dexingworld.hanfu.common.parameter;

import com.dexingworld.hanfu.common.SessionUser;

/**
 * Created by wangpeng on 2016/11/7.
 */
public class SessionParameter  {

    private String accessToken;

    private SessionUser sessionUser;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public SessionUser getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(SessionUser sessionUser) {
        this.sessionUser = sessionUser;
    }
}
