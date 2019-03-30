package com.pby.gamstudy.bean.body;

import com.google.gson.annotations.SerializedName;
import com.pby.gamstudy.bean.Post;
import com.pby.gamstudy.bean.User;

import java.util.List;

public class UserProfileResponseBody {

    private User user;
    @SerializedName("postList")
    private List<Post> postList;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }
}
