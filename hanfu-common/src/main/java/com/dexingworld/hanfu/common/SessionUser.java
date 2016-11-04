package com.dexingworld.hanfu.common;

import java.awt.*;
import java.io.Serializable;
import java.util.List;

/**
 * 用户会话信息
 * Created by wangpeng on 2016/11/4.
 */
public class SessionUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 电话
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickName;

}
