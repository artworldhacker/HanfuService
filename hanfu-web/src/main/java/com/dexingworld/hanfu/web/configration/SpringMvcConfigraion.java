package com.dexingworld.hanfu.web.configration;

import com.dexingworld.hanfu.web.configration.adapter.HanfuRequestMappingHandlerAdapter;
import com.dexingworld.hanfu.web.configration.advice.LogViewResponseBodyAdvice;
import com.dexingworld.hanfu.web.interceptor.SystemInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.ViewResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2016/9/29.
 */
@Configuration
@ComponentScan("com.dexingworld.hanfu.web")
@EnableWebMvc
public class SpringMvcConfigraion extends WebMvcConfigurationSupport{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMvcConfigraion.class);

    private static final boolean jackson2Present = ClassUtils.isPresent(
            "com.fasterxml.jackson.databind.ObjectMapper",
            WebMvcConfigurationSupport.class.getClassLoader())
            && ClassUtils.isPresent("com.fasterxml.jackson.core.JsonGenerator",
            WebMvcConfigurationSupport.class.getClassLoader());

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
    }

   /* @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }*/

    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        List<HandlerMethodArgumentResolver> argumentResolvers = new ArrayList<HandlerMethodArgumentResolver>();
        addArgumentResolvers(argumentResolvers);

        List<HandlerMethodReturnValueHandler> returnValueHandlers = new ArrayList<HandlerMethodReturnValueHandler>();
        addReturnValueHandlers(returnValueHandlers);

        HanfuRequestMappingHandlerAdapter adapter = new HanfuRequestMappingHandlerAdapter();
        adapter.setContentNegotiationManager(mvcContentNegotiationManager());
        adapter.setMessageConverters(getMessageConverters());
        adapter.setWebBindingInitializer(getConfigurableWebBindingInitializer());
        adapter.setCustomArgumentResolvers(argumentResolvers);
        adapter.setCustomReturnValueHandlers(returnValueHandlers);

        if (jackson2Present) {
            List<ResponseBodyAdvice<?>> interceptors = new ArrayList<ResponseBodyAdvice<?>>();
            interceptors.add(new JsonViewResponseBodyAdvice());
            interceptors.add(new LogViewResponseBodyAdvice());
            adapter.setResponseBodyAdvice(interceptors);
        }

        HanfuAsyncSupportConfigurer configurer = new HanfuAsyncSupportConfigurer();
        configureAsyncSupport(configurer);

        if (configurer.getTaskExecutor() != null) {
            adapter.setTaskExecutor(configurer.getTaskExecutor());
        }
        if (configurer.getTimeout() != null) {
            adapter.setAsyncRequestTimeout(configurer.getTimeout());
        }
        adapter.setCallableInterceptors(configurer.getCallableInterceptors());
        adapter.setDeferredResultInterceptors(configurer
                .getDeferredResultInterceptors());

        return adapter;
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        LOGGER.info("LocaleChangeInterceptor");
        return new LocaleChangeInterceptor();
    }

    @Bean
    public SystemInterceptor initializingInterceptor(){
        return new SystemInterceptor();
    }


    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
        registry.addInterceptor(initializingInterceptor());
    }


    /**
     * 静态资源配置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/");
    }



}
