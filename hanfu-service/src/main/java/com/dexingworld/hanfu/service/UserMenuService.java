package com.dexingworld.hanfu.service;

import com.dexingworld.hanfu.repository.entity.Menu;
import com.dexingworld.hanfu.repository.entity.UserMenu;
import com.dexingworld.hanfu.service.base.BaseService;

import java.util.List;

/**
 * Created by wangpeng on 2016/11/7.
 */
public interface UserMenuService extends BaseService<UserMenu,Long>{

    List<Menu> queryUserMenuWithParent(Long userId,Long parentId);
}
