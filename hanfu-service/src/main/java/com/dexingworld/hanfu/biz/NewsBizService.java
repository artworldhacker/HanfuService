package com.dexingworld.hanfu.biz;

import com.dexingworld.hanfu.common.parameter.AddNews;
import com.dexingworld.hanfu.common.parameter.QueryNews;
import com.dexingworld.hanfu.common.parameter.UpdNews;
import com.dexingworld.hanfu.common.response.PageResponse;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.repository.entity.News;

/**
 * Created by wangpeng on 2016/10/10.
 */
public interface NewsBizService {

    PageResponse<News> queryByPage(QueryNews query);

    ResultResponse add(AddNews add);

    ResultResponse update(UpdNews update);

    ResultResponse delete(Long id);
}
