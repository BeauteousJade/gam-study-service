package com.pby.gamstudy.bean;

import java.util.List;

public class User {
    private String id;
    private String nickName;
    private String head;
    private String token;
    private int isFollow;
    private long time;
    private int score;
    private List<Follow> followUserList;
    private List<Follow> fansUserList;


    public User() {
    }

    public User(String id) {
        this.id = id;
    }

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }
}
