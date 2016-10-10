package com.dexingworld.hanfu.web.controller;

import com.dexingworld.hanfu.biz.NewsBizService;
import com.dexingworld.hanfu.common.parameter.AddNews;
import com.dexingworld.hanfu.common.parameter.QueryNews;
import com.dexingworld.hanfu.common.parameter.UpdNews;
import com.dexingworld.hanfu.common.response.PageResponse;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.repository.entity.News;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by wangpeng on 2016/10/10.
 */
@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsBizService newsBizService;


    @RequestMapping("/page")
    public PageResponse<News> getListByPage(QueryNews query){
        return newsBizService.queryByPage(query);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultResponse add(@Valid AddNews add,BindingResult bindingResult){
        ResultResponse resultResponse = new ResultResponse();
        if(bindingResult.hasErrors()){
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            if(StringUtils.isNoneBlank(errorMsg)){
                resultResponse.makeFailure(errorMsg);
            }
        }
        return newsBizService.add(add);
    }


    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResultResponse update(@Valid UpdNews update,BindingResult bindingResult){
        ResultResponse resultResponse = new ResultResponse();
        if(bindingResult.hasErrors()){
            String errorMsg = bindingResult.getFieldError().getDefaultMessage();
            if(StringUtils.isNoneBlank(errorMsg)){
                resultResponse.makeFailure(errorMsg);
            }
        }
        return newsBizService.update(update);
    }


    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResultResponse update(@RequestParam(value = "id",required = false) Long id){
        ResultResponse resultResponse = new ResultResponse();
        if(id == null){
            resultResponse.makeFailure("id不能为空");
        }
        return newsBizService.delete(id);
    }
}
