package com.pby.gamstudy.bean;

public class MessageItem {

    private String id;
    private User fromUser;
    private User toUser;
    private String recentContent;
    private long recentTime;
    private long time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public String getRecentContent() {
        return recentContent;
    }

    public void setRecentContent(String recentContent) {
        this.recentContent = recentContent;
    }

    public long getRecentTime() {
        return recentTime;
    }

    public void setRecentTime(long recentTime) {
        this.recentTime = recentTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
