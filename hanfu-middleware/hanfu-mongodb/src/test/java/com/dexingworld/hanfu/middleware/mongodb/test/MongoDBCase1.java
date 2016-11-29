//package com.dexingworld.hanfu.middleware.mongodb.test;
//
//import com.google.common.collect.Lists;
//import com.mongodb.MongoClient;
//import com.mongodb.MongoCredential;
//import com.mongodb.ServerAddress;
//import com.mongodb.client.MongoDatabase;
//
//import java.util.List;
//
///**
// * Created by wangpeng on 2016/11/21.
// */
//public class MongoDBCase1 {
//
//
//    public static void main(String[] args) {
//        ServerAddress serverAddress = new ServerAddress("127.0.0.1",27017);
//        List<ServerAddress> addresses = Lists.newArrayList();
//        addresses.add(serverAddress);
//
//        //针对需要验证用户名密码的mongodb
//        MongoCredential mongoCredential = MongoCredential.createScramSha1Credential("username","dataBaseName","password".toCharArray());
//        List<MongoCredential> credentials = Lists.newArrayList();
//        credentials.add(mongoCredential);
//        MongoClient mongoClient = new MongoClient(addresses,credentials);
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("hanfu");
//        System.out.println("connected successfully...");
//    }
//}
