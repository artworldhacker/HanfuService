package com.dexingworld.hanfu.web.configration;

import com.dexingworld.hanfu.web.interceptor.SystemInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.ViewResolver;

/**
 * Created by wangpeng on 2016/9/29.
 */
@Configuration
@ComponentScan("com.dexingworld.hanfu.web")
@EnableWebMvc
public class SpringMvcConfigraion extends WebMvcConfigurerAdapter{

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMvcConfigraion.class);

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".html");
        resolver.setExposeContextBeansAsAttributes(true);
        return resolver;
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
