package com.dexingworld.hanfu.service.base;

import com.dexingworld.hanfu.common.response.PageResponse;
import com.dexingworld.hanfu.repository.base.BaseDao;

import java.util.List;

/**
 * Created by wangpeng on 2016/10/10.
 */
public abstract class BaseServiceImpl<T,K> implements BaseService<T,K> {

    protected abstract BaseDao<T, K> getDao();


    @Override
    public T getById(K k) {
        return this.getDao().getById(k);
    }

    @Override
    public List<T> query(T t) {
        return this.getDao().query(t);
    }

    @Override
    public Integer add(T t) {
        return this.getDao().add(t);
    }

    @Override
    public Integer update(T t) {
        return this.getDao().update(t);
    }

    @Override
    public Integer delete(K k) {
        return this.getDao().delete(k);
    }

    @Override
    public PageResponse<T> queryByPage(T t) {
        if (this.getDao() != null && this.getDao() instanceof BaseDao) {
            return new PageResponse<T>(true,this.getDao().count(t),0,0,this.getDao().query(t));
        }
        return null;
    }

    @Override
    public int count(T t) {
        return this.getDao().count(t);
    }

    @Override
    public T get(T t) {
        return this.getDao().get(t);
    }
}
