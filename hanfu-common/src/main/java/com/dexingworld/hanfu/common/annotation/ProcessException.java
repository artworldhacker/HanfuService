package com.dexingworld.hanfu.common.annotation;

import java.lang.annotation.*;

/**
 * 是否处理异常注解类
 * Created by wangpeng on 2016/10/14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.METHOD})
public @interface ProcessException {

    String value() default "";

}
