package com.pby.gamstudy.dao;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pby.gamstudy.CheckSumBuilder;
import com.pby.gamstudy.bean.IMMessage;
import com.pby.gamstudy.bean.IMUser;
import com.pby.gamstudy.bean.Message;
import com.pby.gamstudy.util.gson.GsonUtil;
import okhttp3.*;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Repository
public class IMDao {

    String appKey = "62a6c7a3aa03c227d48a0c1e63b1073f";
    String appSecret = "7ac655bbff79";
    private static final String USER_URL = "https://api.netease.im/nimserver/user/";
    private static final String MESSAGE_URL = "https://api.netease.im/nimserver/msg/";
    private static final String HISTORY_URL = "https://api.netease.im/nimserver/history/";

    private OkHttpClient okHttpClient = new OkHttpClient();

    /**
     * 注册用户
     *
     * @param userId
     * @return
     */
    public String getToken(String userId) {
        Response response = getResponse(USER_URL + "create.action", () -> new FormBody.Builder()
                .add("accid", userId)
                .build());
        String token = null;
        if ((token = getTokenFromResponse(response)) != null) {
            return token;
        }
        response = getResponse(USER_URL + "refreshToken.action", () -> new FormBody.Builder()
                .add("accid", userId)
                .build());
        if ((token = getTokenFromResponse(response)) != null) {
            return token;
        }
        return null;
    }

    /**
     * 发送消息
     *
     * @param message
     * @return
     */
    public boolean sendMessage(Message message) {
        Response response = getResponse(MESSAGE_URL + "sendMsg.action", () -> new FormBody.Builder()
                .add("from", message.getFromUser().getId())
                .add("ope", "0")
                .add("to", message.getToUser().getId())
                .add("type", "0")
                .add("body", formatJson(message.getContent()))
                .build());
        if (response != null && response.body() != null) {
            try {
                if (GsonUtil.getCode(response.body().string()) == 200) {
                    return true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private String formatJson(String message) {
        return "{\"msg\":\"" + message + "\"}";
    }

    public List<Message> findHistoryMessage(String fromUserId, String toUserId, long startTime, long endTime) {
        Response response = getResponse(HISTORY_URL + "querySessionMsg.action", () -> new FormBody.Builder()
                .add("from", fromUserId)
                .add("to", toUserId)
                .add("begintime", String.valueOf(startTime))
                .add("endtime", String.valueOf(endTime))
                .add("limit", String.valueOf(100))
                .add("reverse", "1")
                .build());
        return parseMessageResponse(response);
    }

    private List<Message> parseMessageResponse(Response response) {
        try {
            if (response != null && response.body() != null) {
                final String body = response.body().string();
                JsonObject jsonObject = new JsonParser().parse(body).getAsJsonObject();
                if (GsonUtil.getCode(jsonObject) == 200) {
                    List<Message> messages = new ArrayList<>();
                    JsonArray jsonArray = jsonObject.getAsJsonArray("msgs");
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JsonObject jsonObject1 = jsonArray.get(i).getAsJsonObject();
                            long time = jsonObject1.get("sendtime").getAsLong();
                            String id = jsonObject1.get("msgid").getAsString();
                            String content = jsonObject1.get("body").getAsJsonObject().get("msg").getAsString();
                            String sendUserId = jsonObject1.get("from").getAsString();
                            Message message = new Message();
                            message.setTime(time);
                            message.setId(id);
                            message.setContent(content);
                            message.setSendUserId(sendUserId);
                            messages.add(message);
                        }
                    }
                    return messages;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getTokenFromResponse(Response response) {
        if (response != null && response.body() != null) {
            try {
                IMUser imUser = GsonUtil.getValues(response.body().string(), "info", IMUser.class);
                if (imUser != null) {
                    return imUser.getToken();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
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
