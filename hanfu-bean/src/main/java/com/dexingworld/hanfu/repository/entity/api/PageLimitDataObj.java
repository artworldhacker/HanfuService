package com.dexingworld.hanfu.repository.entity.api;

import java.io.Serializable;

/**
 * Created by wangpeng on 2016/10/10.
 * 分页对象
 */
public class PageLimitDataObj implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 开始条数
     */
    private Integer startRow;


    /**
     * 结束条数
     */
    private Integer endRow;

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }
}
