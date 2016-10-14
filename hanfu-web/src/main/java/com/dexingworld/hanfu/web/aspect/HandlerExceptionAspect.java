package com.dexingworld.hanfu.web.aspect;

import com.alibaba.fastjson.JSONObject;
import com.dexingworld.hanfu.common.SysException;
import com.dexingworld.hanfu.common.annotation.ProcessException;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.google.common.collect.Maps;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理切面
 * Created by wangpeng on 2016/10/14.
 */
@Aspect
@Component
public class HandlerExceptionAspect  implements Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerExceptionAspect.class);

    public HandlerExceptionAspect() {
    }

    @Around("@annotation(com.dexingworld.hanfu.common.annotation.ProcessException)")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        Method method = ((MethodSignature) jp.getSignature()).getMethod();
        ProcessException processException = method.getAnnotation(ProcessException.class);
        if(processException == null){
            throw new IllegalStateException("can not find the annonation ProcessException on the method ["
                    + method.toString()
                    + "]");
        }
        Map<String, Object> args = Maps.newHashMap();
        if (jp.getArgs() != null) {
            // 收集提交的数据
            for (Object obj : jp.getArgs()) {
                args.put(obj.getClass().getSimpleName(), obj);
            }
        }
        ResultResponse resultResponse = (ResultResponse) method.getReturnType().newInstance();
        Object result = null;
        try {
            result = jp.proceed(jp.getArgs());
        } catch (IllegalArgumentException e) {
            return resultResponse.makeFailure(e.getMessage());
        } catch (SysException e) {
            return  resultResponse.makeFailure(e.getMessage());
        } catch (Exception e) {
            return resultResponse.makeFailure(e.getMessage());
        }
        if (LOGGER.isInfoEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append(method.getDeclaringClass().getName());
            sb.append("=>" + method.getName() + " ");
            sb.append(JSONObject.toJSON(args));
            LOGGER.info(sb.toString());
        }
        return  result;
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}
