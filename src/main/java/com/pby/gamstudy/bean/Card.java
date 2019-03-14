package com.pby.gamstudy.bean;

import java.io.Serializable;

public class Card implements Serializable {
    private String id;
    private String userId;
    private String kindId;
    private String oldImageUrl;
    private String editImageUrl;
    private String answer;
    private long time;
    private long updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getKindId() {
        return kindId;
    }

    public void setKindId(String kindId) {
        this.kindId = kindId;
    }

    public String getOldImageUrl() {
        return oldImageUrl;
    }

    public void setOldImageUrl(String oldImageUrl) {
        this.oldImageUrl = oldImageUrl;
    }

    public String getEditImageUrl() {
        return editImageUrl;
    }

    public void setEditImageUrl(String editImageUrl) {
        this.editImageUrl = editImageUrl;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
