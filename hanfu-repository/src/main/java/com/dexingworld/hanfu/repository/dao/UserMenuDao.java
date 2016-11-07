package com.dexingworld.hanfu.repository.dao;

import com.dexingworld.hanfu.repository.MyBatisRepository;
import com.dexingworld.hanfu.repository.base.BaseDao;
import com.dexingworld.hanfu.repository.entity.Menu;
import com.dexingworld.hanfu.repository.entity.UserMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisRepository
public interface UserMenuDao extends BaseDao<UserMenu, Long> {

    List<Menu> queryUserMenuWithParent(@Param("userId")Long userId,@Param("parentId")Long parentId);
}
