package com.example.upimage.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author YuanLi
 * @version 1.0
 * @date 2022/4/13 15:09
 */

@Controller
@Slf4j
public class UploadController {

//    private String path="D:\\IDEA\\filetest\\";
    private String path="/filetest/";
//    private String path="/tmp/";

    @GetMapping("/")
    public String uploadPage() {
        return "upload.html";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String create(@RequestPart MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
//        System.out.println(fileName);
        log.info("==========");
        log.info(fileName);
        log.info("==========");
        String filePath = path + fileName;

        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());
        return "Upload file success : " + dest.getAbsolutePath();
    }

    @GetMapping("qwe")
    public String ttt(@RequestParam String name){
        return "qwe";
    }
}
