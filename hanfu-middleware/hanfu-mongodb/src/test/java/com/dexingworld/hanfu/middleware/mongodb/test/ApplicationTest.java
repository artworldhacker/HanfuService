//package com.dexingworld.hanfu.middleware.mongodb.test;
//
//import com.dexingworld.hanfu.middleware.mongodb.bean.User;
//import com.dexingworld.hanfu.middleware.mongodb.dao.UserRepository;
//import javafx.application.Application;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.SpringApplicationConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * Created by wangpeng on 2016/11/21.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(Application.class)
//public class ApplicationTest {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Before
//    public void setUp(){
//        userRepository.deleteAll();
//    }
//
//    @Test
//    public void test(){
//        // 创建三个User，并验证User总数
//        userRepository.save(new User(1L, "didi", 30));
//        userRepository.save(new User(2L, "mama", 40));
//        userRepository.save(new User(3L, "kaka", 50));
//
//        // 删除一个User，再验证User总数
//        User u = userRepository.findOne(1L);
//        userRepository.delete(u);
//
//        // 删除一个User，再验证User总数
//        u = userRepository.findByUsername("mama");
//        userRepository.delete(u);
//    }
//}
