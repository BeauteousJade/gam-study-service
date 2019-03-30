package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.IMUser;
import com.pby.gamstudy.bean.User;
import com.pby.gamstudy.bean.body.UserProfileResponseBody;
import com.pby.gamstudy.dao.IMDao;
import com.pby.gamstudy.dao.PostDao;
import com.pby.gamstudy.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserService {
    private static final String NICK_NAME = "pby";
    private static final String HEAD = "https://upload.jianshu.io/users/upload_avatars/9124992/c56d68b9-89af-48a2-a93a-48e7dcac778f.jpg";

    @Autowired
    UserDao userDao;
    @Autowired
    IMDao mIMDao;
    @Autowired
    PostDao mPostDao;

    public User findUser(String id) {
        User user = userDao.findUser(id);
        if (user == null) {
            final IMUser imUser = mIMDao.createUser(id);
            if (imUser != null) {
                user = new User();
                user.setId(id);
                user.setToken(imUser.getToken());
                user.setHead(HEAD);
                user.setNickName(NICK_NAME);
                user.setTime(System.currentTimeMillis());
                if (userDao.register(user) != 1) {
                    user = null;
                }
            }
        }
        return user;
    }

    public User findBasicUser(String id) {
        User user = userDao.findBasicUser(id);
        if (user != null) {
            user.setToken(null);
        }
        return user;
    }

    public User findUserNoToken(String id) {
        User user = findUser(id);
        if (user != null) {
            user.setToken(null);
        }
        return user;
    }


    public UserProfileResponseBody getUserProfile(String userId) {
        UserProfileResponseBody userProfileResponseBody = new UserProfileResponseBody();
        userProfileResponseBody.setUser(findUserNoToken(userId));
        userProfileResponseBody.setPostList(mPostDao.findPost(userId));
        return userProfileResponseBody;
    }
}

