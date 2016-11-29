package com.dexingworld.hanfu.middleware.mongodb.config;

import com.mongodb.Mongo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by wangpeng on 2016/11/22.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.dexingworld.hanfu")
public class MongoDbConfigration {

    @Bean
    public MongoFactoryBean mongo(){
        MongoFactoryBean mongo = new MongoFactoryBean();
        mongo.setHost("localhost");
        return mongo;
    }

    /**
     * mongoTemplate Bean
     * @param mongo
     * @return
     */
    @Bean
    public MongoOperations mongoTemplate(Mongo mongo){
        return new MongoTemplate(mongo,"hanfu");
    }
}
