package com.dexingworld.hanfu.repository.base;


import java.util.List;

/**
 * Created by wangpeng on 2016/10/10.
 */
public interface BaseDao<T,K> {

    /**
     * Reason: Repository 层API省略.
     */
    T getById(K k);

    /**
     * Reason: Repository 层API省略.
     */
    List<T> query(T query);

    /**
     * Reason: Repository 层API省略.
     */
    Integer add(T user);

    /**
     * Reason: Repository 层API省略.
     */
    Integer update(T user);

    /**
     * Reason: Repository 层API省略.
     */
    Integer delete(K k);


    int count(T t);

    T get(T t);
}
