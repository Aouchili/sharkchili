package com.example.spring_demo.controller;

import com.example.spring_demo.pojo.MoviesDto;
import com.example.spring_demo.service.MoviesService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;


@RestController
@RequestMapping("/TaykaMovies")
public class MoviesController {


    private static final Log log = LogFactory.getLog(MoviesController.class);
    @Autowired MoviesService moviesService;
    private List<MoviesDto> list = new ArrayList<>();



    @GetMapping
    public List<MoviesDto> getMovies(){
        if (log.isTraceEnabled()){
            log.trace("getMovies()已被调用！");
        }
        list = moviesService.getAllMovies();
        return list;
    }
    @GetMapping("/{id}")
    public MoviesDto getOneMovie(@PathVariable int id){
        if(log.isTraceEnabled()){
            log.trace("getOneMovie"+id);
        }

        try{
            int num = id-1;
            return list.get(num);

        }catch (ResourceNotFoundException e){
            throw e;
        }
    }

    @PostMapping
    public MoviesDto insertOne(@Valid @RequestBody MoviesDto moviesDto){
        if(log.isTraceEnabled()){
            log.trace("请填入需要传递进来的参数："+moviesDto);
        }
        int num = list.size() + 1;
        moviesDto.setId(num);
        list.add(moviesDto);
        return moviesDto;
    }
//
    @PutMapping("/{id}")
    public MoviesDto updateOneMovie(@PathVariable int id,@RequestBody MoviesDto moviesDto){
        if(log.isTraceEnabled()){
            log.trace("updateOne"+id);
        }
        int num = id -1;
        try {
            list.get(num).setYear(moviesDto.getYear());
            list.get(num).setPosterurl(moviesDto.getPosterurl());
            list.get(num).setTitle(moviesDto.getTitle());
            return list.get(num);
        }catch (ResourceNotFoundException e){
            throw e;
        }
    }

    @PostMapping(value = "/{id}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhoto(@PathVariable int id, @RequestParam("photo")MultipartFile imgFile) throws Exception {
        if (log.isTraceEnabled()) {
            log.trace("接收到文件" + id + "收到文件" + imgFile.getOriginalFilename());
        }
        //保存文件
        FileOutputStream fos = new FileOutputStream("target/demo_imgs/" + imgFile.getOriginalFilename());
        IOUtils.copy(imgFile.getInputStream(),fos);
        fos.close();
    }

    @PostMapping(value = "/upload_photo",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addPhoto( @RequestParam("photo")MultipartFile imgFile) throws Exception {
        String org_name = imgFile.getOriginalFilename();
        if (log.isTraceEnabled()) {
            log.trace("接收到文件" + "," + "文件原名为：" + org_name);
        }
        //保存文件
        String suffix = org_name.substring(org_name.lastIndexOf("."));
        System.out.print(org_name);
        System.out.print(suffix);
        FileOutputStream fos = new FileOutputStream("target/demo_imgs/" + "1551" + suffix);
        IOUtils.copy(imgFile.getInputStream(),fos);
        fos.close();
    }

    @GetMapping(value = "/{id}/img",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImg(@PathVariable int id) throws Exception{
        if (log.isTraceEnabled()){
            log.trace("getImg("+ id +")");
        }
        String imgFile = "src/test/imgs/img-1.jpg";
        InputStream is = new FileInputStream(imgFile);
        return IOUtils.toByteArray(is);
    }


}
