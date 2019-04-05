package com.pby.gamstudy.controller;

import com.pby.gamstudy.service.ApkService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/apk")
public class ApkController {

    @Autowired
    ApkService apkService;

    @PostMapping("/checkUpdate")
    public String checkUpdate(@Param("code") String code) {
        return apkService.checkUpdate(code);
    }

    //文件下载相关代码
    @RequestMapping("/downloadApk")
    public void downloadApk(@Param("url") String url, HttpServletResponse response) {
        apkService.downloadApk(url, response);
    }
}
