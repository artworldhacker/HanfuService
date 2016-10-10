package com.dexingworld.hanfu.repository.dao;


import com.dexingworld.hanfu.repository.MyBatisRepository;
import com.dexingworld.hanfu.repository.base.BaseDao;
import com.dexingworld.hanfu.repository.entity.News;

@MyBatisRepository
public interface NewsDao extends BaseDao<News, Long> {
}
