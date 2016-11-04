package com.dexingworld.hanfu.repository.dao;

import com.dexingworld.hanfu.repository.MyBatisRepository;
import com.dexingworld.hanfu.repository.base.BaseDao;
import com.dexingworld.hanfu.repository.entity.User;

@MyBatisRepository
public interface UserDao extends BaseDao<User, Long> {
}
