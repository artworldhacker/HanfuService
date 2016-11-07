package com.dexingworld.hanfu.web.aspect;

import com.dexingworld.hanfu.common.SessionUser;
import com.dexingworld.hanfu.common.enums.SysCodeEnum;
import com.dexingworld.hanfu.common.parameter.SessionParameter;
import com.dexingworld.hanfu.common.response.ResultResponse;
import com.dexingworld.hanfu.repository.entity.User;
import com.dexingworld.hanfu.session.SessionProcess;
import com.dexingworld.hanfu.utils.StringUtils;
import com.dexingworld.hanfu.web.annotation.NeedLogin;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 用户会话切面
 * Created by wangpeng on 2016/11/7.
 */
@Aspect
@Component
public class SessionAspect implements Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandlerExceptionAspect.class);

    @Autowired
    private SessionProcess sessionProcess;

    @Around("execution(* com.dexingworld.hanfu.web.controller..*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{
        Object[] args = pjp.getArgs();
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null) {
            return pjp.proceed();
        }
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();//登陆方法
        NeedLogin needLogin = method.getAnnotation(NeedLogin.class);//判断方法是否需要登陆
        if(needLogin == null){//不需要登陆
            return pjp.proceed();
        }
        HttpServletRequest request = requestAttributes.getRequest();//获取request
        String accessToken = sessionProcess.getAcessToken(request);
        if(StringUtils.isEmpty(accessToken)){
            return new ResultResponse(SysCodeEnum.NO_LOGIN);
        }
        User user = sessionProcess.getSessionUser(accessToken);
        if(user == null){
            return new ResultResponse(SysCodeEnum.NO_LOGIN);
        }
        for (int i = 0; i< args.length;i++){//请求参数
            Object arg = args[i];
            if(arg instanceof SessionParameter){
                SessionParameter sessionParameter = (SessionParameter)arg;
                sessionParameter.setAccessToken(accessToken);
                if(user != null){
                    SessionUser sessionUser = new SessionUser();
                    BeanUtils.copyProperties(user,sessionUser);
                    sessionParameter.setSessionUser(sessionUser);
                }
            }
        }


        return pjp.proceed();
    }



    @Override
    public int getOrder() {
        return 1;
    }
}
