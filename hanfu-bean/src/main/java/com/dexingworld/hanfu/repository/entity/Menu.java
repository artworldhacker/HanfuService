package com.dexingworld.hanfu.repository.entity;

import com.dexingworld.hanfu.repository.entity.api.BasePageDataObj;

import java.util.Date;

/**
 * 
 */
public class Menu extends BasePageDataObj {
    private static final long serialVersionUID = 1L;
    private Long id;
    /**
     * 菜单Id
     */
    private Long menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 点击链接
     */
    private String linkUrl;
    /**
     * 图片样式
     */
    private String icon;
    /**
     * 上级菜单Id
     */
    private Long parentMenuId;
    /**
     * 排序id
     */
    private Long orderClomun;
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
    public Long getMenuId() {
        return this.menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public String getMenuName() {
        return this.menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getLinkUrl() {
        return this.linkUrl;
    }
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    public String getIcon() {
        return this.icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Long getParentMenuId() {
        return this.parentMenuId;
    }
    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }
    public Long getOrderClomun() {
        return this.orderClomun;
    }
    public void setOrderClomun(Long orderClomun) {
        this.orderClomun = orderClomun;
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
