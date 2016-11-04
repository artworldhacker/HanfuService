package com.dexingworld.hanfu.biz.impl;

import com.dexingworld.hanfu.biz.MenuBizService;
import com.dexingworld.hanfu.common.parameter.AddMenu;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.repository.entity.Menu;
import com.dexingworld.hanfu.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by wangpeng on 2016/11/4.
 */
@Service
public class MenuBizServiceImpl implements MenuBizService {

    @Autowired
    private MenuService menuService;

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
}
