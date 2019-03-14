package com.pby.gamstudy.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {

    @Value("${file.rootPath}")
    String ROOT_PATH;
    @Value("${localhost}")
    String localHost;


    @PostMapping("/upload")
    @ResponseBody
    public void upload(HttpServletRequest request) {
        final List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        final String basePath = ROOT_PATH + File.separator + "image";
        for (MultipartFile multipartFile : files) {
            File file = new File(basePath + File.separator + multipartFile.getOriginalFilename());
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                multipartFile.transferTo(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
