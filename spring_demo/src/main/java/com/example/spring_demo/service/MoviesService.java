package com.example.spring_demo.service;

import com.example.spring_demo.dao.MoviesDao;
import com.example.spring_demo.pojo.MoviesDto;
import org.bson.Document;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MoviesService {

    public List<MoviesDto> getAllMovies(){
        MoviesDao moviesDao = new MoviesDao();
        return moviesDao.getAll();
    }
}

