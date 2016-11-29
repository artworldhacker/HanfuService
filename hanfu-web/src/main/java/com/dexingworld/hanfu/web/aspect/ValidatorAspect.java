package com.dexingworld.hanfu.web.aspect;

import com.dexingworld.hanfu.utils.ValidatorUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * 数据校验切面
 * Created by wangpeng on 2016/11/14.
 */
@Aspect
@Component
public class ValidatorAspect implements Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidatorAspect.class);

    @Around("execution(* com.dexingworld.hanfu.web.controller..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        //校验
        Object[] parameters = pjp.getArgs();
        if(parameters != null && parameters.length > 0){
            for (Object obj :parameters ){
                ValidatorUtils.validator(obj);
            }
        }
        return pjp.proceed();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
