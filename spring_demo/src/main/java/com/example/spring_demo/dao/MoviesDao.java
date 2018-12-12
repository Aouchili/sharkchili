package com.example.spring_demo.dao;

import com.example.spring_demo.pojo.MoviesDto;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MoviesDao {
    public List<MoviesDto> getAll() {

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient("192.168.1.7",27017);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("dustman");
        MongoCollection<Document> collection  = mongoDatabase.getCollection("subject");
        System.out.print(collection.toString());
        List<MoviesDto> list = new ArrayList<MoviesDto>();
        FindIterable<Document> findIterable = collection.find();
        int id = 1;
        for (Document iter:findIterable){
            String title = iter.get("title").toString();
            String year = iter.get("year").toString();
            String posterurl = (String) iter.get("posterurl").toString();
            MoviesDto moviesDto = new MoviesDto(id,title,year,posterurl);
            id = id + 1;

            list.add(moviesDto);
        }

        return list;
    }
}
