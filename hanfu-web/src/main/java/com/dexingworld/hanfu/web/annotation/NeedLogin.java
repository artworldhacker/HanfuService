package com.dexingworld.hanfu.web.annotation;

import java.lang.annotation.*;

/**
 * Created by wangpeng on 2016/11/7.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD})
public @interface NeedLogin {
}
