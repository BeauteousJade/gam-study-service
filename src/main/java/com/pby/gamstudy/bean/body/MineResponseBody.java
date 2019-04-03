package com.pby.gamstudy.bean.body;

import com.pby.gamstudy.bean.MapBean;
import com.pby.gamstudy.bean.User;

import java.util.List;

public class MineResponseBody {

    private User user;
    private List<MapBean> list;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MapBean> getList() {
        return list;
    }

    public void setList(List<MapBean> list) {
        this.list = list;
    }
}
