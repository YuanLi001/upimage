package com.example.upimage.controller;

import cn.hutool.core.io.FileUtil;
import com.example.upimage.utils.AppException;
import com.example.upimage.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author YuanLi
 * @version 1.0
 * @date 2022/4/13 15:09
 */

@Controller
@Slf4j
public class UploadController {

    private String path="D:\\IDEA\\filetest\\";
//    private String path="/filetest/";
//    private String path="/tmp/";

    @GetMapping("/")
    public String uploadPage() {
        return "upload.html";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Result<String> create(@RequestPart MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        //判断文件名是否为空
        if( fileName.equals("") ){
            throw new AppException("Upload failed:文件名为空");
//            return new Result<>(null,false,"Upload failed:文件名为空");
        }
        //判断文件是否已存在
        String filePath = path + fileName;
        File dest = new File(filePath);
        if( dest.exists() ){
            throw new AppException("Upload failed:该文件名已存在");
//            return new Result<>(null,false,"Upload failed:该文件名已存在");
        }
        log.info("==========");
        log.info(fileName);
        log.info("==========");
        //写文件
        Files.copy(file.getInputStream(), dest.toPath());
        return new Result<>(null,true,"Upload file success : " + dest.getAbsolutePath());
    }

    @GetMapping("/download")
    public  ResponseEntity<Resource> fileDownload(@RequestParam String fileName) throws FileNotFoundException {
//        log.info("==========");
//        log.info(fileName);
//        log.info("==========");
        String filePath = path + fileName;
        File file=new File(filePath);
        if(!file.exists()){
//            throw new Exception("该文件不存在");
            throw new AppException("该文件不存在");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+fileName);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }

    //获得所有文件列表
    @GetMapping("/allFiles")
    @ResponseBody
    public Result<String[]> getAllFiles(){
        File file=new File(path);
        String[] list = file.list();
        return new Result<>(list,true,"success");
    }

    @GetMapping("qwe")
    @ResponseBody
    public String ttt(@RequestParam String name){
        return "qwe";
    }
}
