package com.dexingworld.hanfu.quick.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangpeng on 2016/10/11.
 */
@Configuration
@ComponentScan(basePackages = {"com.dexingworld.hanfu"})
@EnableConfigurationProperties
@EnableAutoConfiguration
public class QuickBootstartup{


    public static void main(String[] args) {
        SpringApplication.run(QuickBootstartup.class);
    }
}
