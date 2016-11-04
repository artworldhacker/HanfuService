package com.dexingworld.hanfu.service.impl;

import com.dexingworld.hanfu.repository.base.BaseDao;
import com.dexingworld.hanfu.repository.dao.UserDao;
import com.dexingworld.hanfu.repository.entity.User;
import com.dexingworld.hanfu.service.UserService;
import com.dexingworld.hanfu.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangpeng on 2016/11/4.
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User,Long> implements UserService {

    @Autowired
    private UserDao userDao;
    @Override
    protected BaseDao<User, Long> getDao() {
        return userDao;
    }
}
