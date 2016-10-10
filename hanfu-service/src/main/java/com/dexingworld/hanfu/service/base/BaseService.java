package com.dexingworld.hanfu.service.base;

import com.dexingworld.hanfu.common.response.PageResponse;

import java.util.List;

/**
 * Created by wangpeng on 2016/10/10.
 */
public interface BaseService<T,K> {


    T getById(K k);


    List<T> query(T t);


    Integer add(T t);


    Integer update(T t);


    Integer delete(K k);

    int count(T t);

    PageResponse<T> queryByPage(T t);

    T get(T t);
}
