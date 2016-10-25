package com.dexingworld.hanfu.crawler.httpclient;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/7/1.
 * 代理类设置
 */
public class Proxy implements Serializable{

    private String username;

    private String password;

    private String ip;

    private int port;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
