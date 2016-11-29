package com.dexingworld.hanfu.middleware.mongodb.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;

/**
 * Created by wangpeng on 2016/11/22.
 */
@Configuration
@EnableMongoRepositories("com.dexingworld.hanfu")
public class MongoConfig extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {//指定数据库名称
        return "hanfu";
    }

    @Override
    public Mongo mongo() throws Exception {//创建Mongo客户端
        return new MongoClient("localhost",27017);//端口默认27017，可不填
    }

    @Autowired
    private Environment env;

    /**
     * 带用户名密码
     * @return
     * @throws Exception
     */
    public Mongo mongo1() throws Exception {
        MongoCredential credential = MongoCredential.createMongoCRCredential(
                env.getProperty("mongo.username"),"hanfu",
                env.getProperty("mongo.password").toCharArray()
        );
        return new MongoClient(
                new ServerAddress("localhost",27017),
                Arrays.asList(credential)
        );

    }
}
