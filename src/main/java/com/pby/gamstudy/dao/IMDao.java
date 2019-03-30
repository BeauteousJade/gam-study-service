package com.pby.gamstudy.dao;

import com.pby.gamstudy.CheckSumBuilder;
import com.pby.gamstudy.bean.IMMessage;
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
    private static final String USER_URL = "https://api.netease.im/nimserver/user/";
    private static final String MESSAGE_URL = "https://api.netease.im/nimserver/msg/";

    private OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * 注册用户
     *
     * @param userId
     * @return
     */
    public IMUser createUser(String userId) {
        Response response = getResponse(USER_URL + "create.action", () -> new FormBody.Builder()
                .add("accid", userId)
                .build());
        if (response != null && response.body() != null) {
            try {
                return GsonUtil.getValues(response.body().string(), "info", IMUser.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public boolean sendMessage(IMMessage imMessage) {
        Response response = getResponse(MESSAGE_URL + "sendMsg.action", () -> new FormBody.Builder()
                .add("from", imMessage.getFromUserId())
                .add("ope", "0")
                .add("to", imMessage.getToUserId())
                .add("type", "0")
                .add("body", imMessage.getContent())
                .build());
        if (response != null && response.body() != null) {
            try {
                if (GsonUtil.getCode(response.body().string()) == 200) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private Response getResponse(String url, RequestBodyCallback callback) {
        Request.Builder requestBuilder = generateRequestBuilder(url);
        RequestBody requestBody = callback.generateRequestBody();
        Request request = requestBuilder.post(requestBody).build();
        try {
            return okHttpClient.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Request.Builder generateRequestBuilder(String url) {
        Request.Builder builder = new Request.Builder().url(url);
        String nonce = String.valueOf((int) (Math.random() * Integer.MAX_VALUE));
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce, curTime);
        builder.addHeader("AppKey", appKey);
        builder.addHeader("Nonce", nonce);
        builder.addHeader("CurTime", curTime);
        builder.addHeader("CheckSum", checkSum);
        builder.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        return builder;
    }

    private interface RequestBodyCallback {
        RequestBody generateRequestBody();
    }
}
