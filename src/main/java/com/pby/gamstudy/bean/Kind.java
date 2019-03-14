package com.pby.gamstudy.bean;

public class Kind {
    private String id;
    private String userId;
    private String cover;
    private String name;
    private long time;
    private int count;
    private long recentBrowserTime;

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getRecentBrowserTime() {
        return recentBrowserTime;
    }

    public void setRecentBrowserTime(long recentBrowserTime) {
        this.recentBrowserTime = recentBrowserTime;
    }
}
