package com.example.spring_demo.Utils;

import com.example.spring_demo.pojo.MoviesDto;
import org.bson.Document;

public class DaoFormUtils {
    public MoviesDto Document2Subject(Document document){
        try {

            String title = document.get("title").toString();
            String year = document.get("year").toString();
            String posterurl = (String) document.get("posterurl");
            MoviesDto subject = new MoviesDto(0,title,year,posterurl);
            return subject;
        }catch (Exception e) {

            throw new RuntimeException(e);
        }
    }
}
