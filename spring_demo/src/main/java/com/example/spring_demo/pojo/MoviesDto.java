package com.example.spring_demo.pojo;

import org.springframework.context.annotation.Bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class MoviesDto {
    private int id;
    private String title;
    private String year;
    private String posterurl;

    public MoviesDto(){

    }
    public MoviesDto(int id,String title,String year,String posterurl){
        this.id = id;
        this.title = title;
        this.year = year;
        this.posterurl = posterurl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPosterurl(String posterurl) {
        this.posterurl = posterurl;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getPosterurl() {
        return posterurl;
    }
    @Override
    public String toString(){
        return this.getClass().getName()+","+getId()+","+getTitle();
    }
}
