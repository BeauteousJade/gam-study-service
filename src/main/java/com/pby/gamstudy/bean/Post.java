package com.pby.gamstudy.bean;

import java.util.List;

public class Post {

    private String id;
    private User user;
    private String content;
    private List<String> imageUrlList;
    private List<User> likeUserList;
    private List<Comment> commentList;
    private long time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageUrlList() {
        return imageUrlList;
    }

    public void setImageUrlList(List<String> imageUrlList) {
        this.imageUrlList = imageUrlList;
    }

    public List<User> getLikeUserList() {
        return likeUserList;
    }

    public void setLikeUserList(List<User> likeUserList) {
        this.likeUserList = likeUserList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
