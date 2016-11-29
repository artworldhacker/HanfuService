package com.dexingworld.hanfu.middleware.mail.config;

import com.dexingworld.hanfu.common.GlobalConsts;
import com.dexingworld.hanfu.middleware.mail.bean.MailConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * mail基础配置类
 * Created by wangpeng on 2016/11/28.
 */
@Configuration
public class MailConfigration {


    /**
     * 邮件资源配置
     */
    @Autowired
    private MailConfigProperties properties;

    @Bean(name = "javaMailSender")
    public JavaMailSender mailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(properties.getHost());
        mailSender.setUsername(properties.getUsername());
        mailSender.setPassword(properties.getPassword());
        mailSender.setDefaultEncoding(GlobalConsts.DEFAULT_ENCODING);
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.smtp.auth",properties.getSmtp().isAuth());
        javaMailProperties.put("mail.smtp.timeout",properties.getSmtp().getTimeout());
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
}
