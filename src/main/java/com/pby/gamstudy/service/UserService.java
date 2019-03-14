package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.User;
import com.pby.gamstudy.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserService {
    private static final String NICK_NAME = "pby";
    private static final String HEAD = "https://upload.jianshu.io/users/upload_avatars/9124992/c56d68b9-89af-48a2-a93a-48e7dcac778f.jpg";

    @Autowired
    UserDao userDao;

    public User  findUser(String id) {
        userDao.register(id, HEAD, NICK_NAME, System.nanoTime());
        return userDao.findUser(id);
    }
}
