package com.dexingworld.hanfu.common.response;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangpeng on 2016/11/4.
 */
public class MenuView implements Serializable {

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

    private List<MenuView> children;

    private boolean checked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Long getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Long parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public Long getOrderClomun() {
        return orderClomun;
    }

    public void setOrderClomun(Long orderClomun) {
        this.orderClomun = orderClomun;
    }

    public List<MenuView> getChildren() {
        return children;
    }

    public void setChildren(List<MenuView> children) {
        this.children = children;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
