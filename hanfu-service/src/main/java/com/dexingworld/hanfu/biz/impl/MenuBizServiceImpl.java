package com.dexingworld.hanfu.biz.impl;

import com.dexingworld.hanfu.biz.MenuBizService;
import com.dexingworld.hanfu.common.SessionUser;
import com.dexingworld.hanfu.common.enums.SysCodeEnum;
import com.dexingworld.hanfu.common.parameter.AddMenu;
import com.dexingworld.hanfu.common.parameter.QueryMenuParameter;
import com.dexingworld.hanfu.common.response.MenuView;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.repository.entity.Menu;
import com.dexingworld.hanfu.service.MenuService;
import com.dexingworld.hanfu.service.UserMenuService;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangpeng on 2016/11/4.
 */
@Service
public class MenuBizServiceImpl implements MenuBizService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MenuBizServiceImpl.class);

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserMenuService userMenuService;

    @Override
    @Transactional
    public ResultResponse add(AddMenu addMenu) {
        ResultResponse resultResponse = new ResultResponse();
        Menu menu = new Menu();
        BeanUtils.copyProperties(addMenu,menu);
        Date now = new Date();
        menu.setCreateTime(now);
        menu.setUpdateTime(now);
        menuService.add(menu);
        return resultResponse.makeSuccessful();
    }


    /**
     * 查询用户所拥有的菜单
     * @param queryMenuParameter
     * @return
     */
    @Override
    public ResultResponse queryUserMenu(QueryMenuParameter queryMenuParameter) {
        SessionUser sessionUser = queryMenuParameter.getSessionUser();
        if(sessionUser  == null){
            LOGGER.error("用户未登陆");
            return new ResultResponse(SysCodeEnum.NO_LOGIN);
        }
        Long userId = sessionUser.getId();//当前登陆用户Id
        List<MenuView> list = queryMenuViewByUser(userId,0l);
        ResultResponse resultResponse = new ResultResponse();
        resultResponse.setResult(list);
        return resultResponse.makeSuccessful();
    }


    /**
     * 递归查询菜单以及下属菜单
     * @param userId
     * @param parentId
     * @return
     */
    private List<MenuView> queryMenuViewByUser(Long userId,Long parentId){
        List<MenuView> list = Lists.newArrayList();
        List<Menu> menuList = userMenuService.queryUserMenuWithParent(userId,parentId);
        if(menuList != null && menuList.size() > 0){
            for (Menu menu  : menuList){
                MenuView menuView = new MenuView();
                BeanUtils.copyProperties(menu,menuView);
                List<MenuView> children = queryMenuViewByUser(userId,menuView.getMenuId());
                if(children != null && children.size() > 0){
                    menuView.setChildren(children);
                }
                list.add(menuView);
            }
        }
        return list;
    }
}
