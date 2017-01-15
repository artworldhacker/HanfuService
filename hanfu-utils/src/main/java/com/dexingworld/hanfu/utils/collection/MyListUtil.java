package com.dexingworld.hanfu.utils.collection;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wangpeng on 2016/12/1.
 */
public class MyListUtil {

    /**
     * list集合截取，从start处开始截取，截取length长度子串，如果length大于源list大小，则截取到list大小处
     * @param sourceList
     * @param start
     * @param length
     * @param <T>
     * @return
     */
    public static <T> List<T> subList(List<T> sourceList,int start, int length){
        if (CollectionUtils.isEmpty(sourceList)){
            return Lists.newArrayList();
        }
        if (start + length >= sourceList.size())
        {
            return sourceList.subList(start, sourceList.size());
        } else
        {
            return sourceList.subList(start, start + length);
        }
    }
}
