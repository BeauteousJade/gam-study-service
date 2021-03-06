package com.pby.gamstudy.controller;

import com.pby.gamstudy.CheckSumBuilder;
import okhttp3.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {


    String appKey = "62a6c7a3aa03c227d48a0c1e63b1073f";
    String appSecret = "7ac655bbff79";


    @PostMapping(value = "/get")
    public String test(@Param("id") String id) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url("https://api.netease.im/nimserver/user/refreshToken.action");
        String nonce = String.valueOf((int) (Math.random() * Integer.MAX_VALUE));
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        builder.addHeader("AppKey", appKey);
        builder.addHeader("Nonce", nonce);
        builder.addHeader("CurTime", curTime);
        builder.addHeader("CheckSum", checkSum);
        builder.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        RequestBody requestBody = new FormBody.Builder()
                .add("accid", id)
                .build();
        Request request = builder.post(requestBody).build();

        Call newCall = okHttpClient.newCall(request);
        try {
            return newCall.execute().body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
