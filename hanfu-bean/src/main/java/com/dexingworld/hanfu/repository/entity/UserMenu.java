package com.dexingworld.hanfu.repository.entity;

import com.dexingworld.hanfu.repository.entity.api.BasePageDataObj;

import java.util.Date;

/**
 * 
 */
public class UserMenu extends BasePageDataObj {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 菜单Id
     */
    private Long menuId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return this.userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getMenuId() {
        return this.menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Date getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
