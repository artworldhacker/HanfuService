package com.dexingworld.hanfu.common.parameter;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Created by wangpeng on 2016/11/4.
 */
public class AddMenu extends SessionParameter{

    private static final long serialVersionUID = 1L;
    /**
     * 菜单Id
     */
    private Long menuId;
    /**
     * 菜单名称
     */
    @NotEmpty(message = "菜单名称不能为空")
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
}
