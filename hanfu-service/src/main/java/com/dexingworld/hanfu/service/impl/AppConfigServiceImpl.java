package com.dexingworld.hanfu.service.impl;

import com.dexingworld.hanfu.repository.dao.AppConfigDao;
import com.dexingworld.hanfu.repository.entity.AppConfig;
import com.dexingworld.hanfu.service.AppConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangpeng on 2016/10/8.
 */
@Service("appConfigService")
@Transactional
public class AppConfigServiceImpl implements AppConfigService {


    @Autowired
    private AppConfigDao dao;


    @Override
    public AppConfig getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public List<AppConfig> query(AppConfig query) {
        return dao.query(query);
    }

    @Override
    public Integer insert(AppConfig user) {
        return dao.insert(user);
    }

    @Override
    public Integer update(AppConfig user) {
        return dao.update(user);
    }

    @Override
    public Integer delete(Integer id) {
        return dao.delete(id);
    }
}
