package com.pby.gamstudy.bean;

import java.util.List;

public class User {
    private String id;
    private String nickName;
    private String head;
    private String token;
    private long time;
    private List<Follow> followUserList;
    private List<Follow> fansUserList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    public List<Follow> getFollowUserList() {
        return followUserList;
    }

    public void setFollowUserList(List<Follow> followUserList) {
        this.followUserList = followUserList;
    }

    public List<Follow> getFansUserList() {
        return fansUserList;
    }

    public void setFansUserList(List<Follow> fansUserList) {
        this.fansUserList = fansUserList;
    }
}
