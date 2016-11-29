//package com.dexingworld.hanfu.middleware.mongodb.test;
//
//
//import com.google.common.collect.Lists;
//import com.mongodb.MongoClient;
//import com.mongodb.client.FindIterable;
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoCursor;
//import com.mongodb.client.MongoDatabase;
//import org.bson.Document;
//
//import java.util.List;
//
///**
// * Created by wangpeng on 2016/11/21.
// */
//public class MongoDBCase4 {
//
//
//    public static void main(String[] args) {
//        MongoClient mongoClient = new MongoClient("127.0.0.1",27017);
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("hanfu");
//        System.out.println("connected successfully.....");
//
//        MongoCollection<Document> collections = mongoDatabase.getCollection("test");
//        System.out.println("集合选择成功!!!");
//
//        //检索
//
//        FindIterable<Document> findIterable = collections.find();
//        MongoCursor<Document> mongoCursor = findIterable.iterator();
//        while (mongoCursor.hasNext()){
//            System.out.println(mongoCursor.next());
//        }
//
//    }
//}
