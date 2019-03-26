package com.pby.gamstudy.dao;

import com.pby.gamstudy.CheckSumBuilder;
import com.pby.gamstudy.bean.IMUser;
import com.pby.gamstudy.util.gson.GsonUtil;
import okhttp3.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;


@Repository
public class IMDao {

    String appKey = "62a6c7a3aa03c227d48a0c1e63b1073f";
    String appSecret = "7ac655bbff79";
    String imBaseUrl = "https://api.netease.im/nimserver/user/";

    public IMUser create(String userId) {
        Response response = getResponse(imBaseUrl + "create.action", userId);
        if (response != null && response.body() != null) {
            try {
                return GsonUtil.getValues(response.body().string(), "info", IMUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    private Response getResponse(String url, String accid) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder().url(url);
        String nonce = String.valueOf((int) (Math.random() * Integer.MAX_VALUE));
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        builder.addHeader("AppKey", appKey);
        builder.addHeader("Nonce", nonce);
        builder.addHeader("CurTime", curTime);
        builder.addHeader("CheckSum", checkSum);
        builder.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        RequestBody requestBody = new FormBody.Builder()
                .add("accid", accid)
                .build();
        Request request = builder.post(requestBody).build();
        try {
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}