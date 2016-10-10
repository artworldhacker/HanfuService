package com.dexingworld.hanfu.service.impl;

import com.dexingworld.hanfu.repository.base.BaseDao;
import com.dexingworld.hanfu.repository.dao.NewsDao;
import com.dexingworld.hanfu.repository.entity.News;
import com.dexingworld.hanfu.service.NewsService;
import com.dexingworld.hanfu.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangpeng on 2016/10/10.
 */
@Service
public class NewsServiceImpl extends BaseServiceImpl<News,Long> implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    protected BaseDao<News, Long> getDao() {
        return newsDao;
    }
}
