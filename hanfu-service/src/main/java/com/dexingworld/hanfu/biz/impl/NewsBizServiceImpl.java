package com.dexingworld.hanfu.biz.impl;

import com.dexingworld.hanfu.biz.NewsBizService;
import com.dexingworld.hanfu.common.parameter.AddNews;
import com.dexingworld.hanfu.common.parameter.QueryNews;
import com.dexingworld.hanfu.common.parameter.UpdNews;
import com.dexingworld.hanfu.common.response.PageResponse;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.repository.entity.News;
import com.dexingworld.hanfu.service.NewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangpeng on 2016/10/10.
 */
@Service
public class NewsBizServiceImpl implements NewsBizService {

    @Autowired
    private NewsService newsService;

    @Override
    public PageResponse<News> queryByPage(QueryNews query) {
        News news = new News();
        BeanUtils.copyProperties(query,news);
        return newsService.queryByPage(news);
    }

    @Override
    public ResultResponse add(AddNews add) {
        ResultResponse resultResponse = new ResultResponse();
        News news = new News();
        BeanUtils.copyProperties(add,news);
        newsService.add(news);
        return resultResponse.makeSuccessful();
    }

    @Override
    public ResultResponse update(UpdNews update) {
        ResultResponse resultResponse = new ResultResponse();
        News news = new News();
        BeanUtils.copyProperties(update,news);
        newsService.update(news);
        return resultResponse.makeSuccessful();
    }

    @Override
    public ResultResponse delete(Long id) {
        ResultResponse resultResponse = new ResultResponse();
        newsService.delete(id);
        return resultResponse.makeSuccessful();
    }
}
