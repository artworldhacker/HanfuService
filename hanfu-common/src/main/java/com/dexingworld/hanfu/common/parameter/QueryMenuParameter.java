package com.dexingworld.hanfu.common.parameter;

/**
 * Created by wangpeng on 2016/11/7.
 */
public class QueryMenuParameter extends  SessionParameter {

    private Long menuId;

    private Long parentMenuId;

    private Long userId;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
