package com.dexingworld.hanfu.repository;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by wangpeng on 2016/10/8.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisRepository {
    String value() default "";
}