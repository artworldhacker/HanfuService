package com.dexingworld.hanfu.service.impl;

import com.dexingworld.hanfu.repository.base.BaseDao;
import com.dexingworld.hanfu.repository.dao.UserMenuDao;
import com.dexingworld.hanfu.repository.entity.Menu;
import com.dexingworld.hanfu.repository.entity.UserMenu;
import com.dexingworld.hanfu.service.UserMenuService;
import com.dexingworld.hanfu.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangpeng on 2016/11/7.
 */
@Service
public class UserMenuServiceImpl extends BaseServiceImpl<UserMenu,Long> implements UserMenuService {

    @Autowired
    private UserMenuDao userMenuDao;

    @Override
    protected BaseDao<UserMenu, Long> getDao() {
        return userMenuDao;
    }

    @Override
    public List<Menu> queryUserMenuWithParent(Long userId, Long parentId) {
        return userMenuDao.queryUserMenuWithParent(userId,parentId);
    }
}
