package com.dexingworld.hanfu.service.impl;

import com.dexingworld.hanfu.repository.base.BaseDao;
import com.dexingworld.hanfu.repository.dao.MenuDao;
import com.dexingworld.hanfu.repository.entity.Menu;
import com.dexingworld.hanfu.service.MenuService;
import com.dexingworld.hanfu.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangpeng on 2016/11/4.
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,Long> implements MenuService {

    @Autowired
    private MenuDao menuDao;
    @Override
    protected BaseDao<Menu, Long> getDao() {
        return menuDao;
    }
}
