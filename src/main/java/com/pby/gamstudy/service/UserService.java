package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.MapBean;
import com.pby.gamstudy.bean.User;
import com.pby.gamstudy.bean.body.MineResponseBody;
import com.pby.gamstudy.bean.body.UserProfileResponseBody;
import com.pby.gamstudy.dao.*;
import com.pby.gamstudy.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserService {
    private static final String NICK_NAME = "pby";
    private static final String HEAD = "https://upload.jianshu.io/users/upload_avatars/9124992/c56d68b9-89af-48a2-a93a-48e7dcac778f.jpg";
    private static final String AVATAR_PATH = "avatar";


    @Value("${file.rootPath}")
    String rootPath;
    @Value("${localHost}")
    String localHost;

    @Autowired
    UserDao userDao;
    @Autowired
    IMDao imDao;
    @Autowired
    PostDao postDao;
    @Autowired
    KindDao kindDao;
    @Autowired
    CardDao cardDao;

    public User findUser(String id) {
        User user = userDao.findUser(id);
        if (user == null) {
            final String token = imDao.getToken(id);
            if (token != null) {
                user = new User();
                user.setId(id);
                user.setToken(token);
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

    public User findUserNoToken(String fromUserId, String toUserId) {
        User user = userDao.findUserWithFollow(fromUserId, toUserId);
        if (user != null) {
            user.setToken(null);
        }
        return user;
    }


    public UserProfileResponseBody getUserProfile(String fromUserId, String toUserId) {
        UserProfileResponseBody userProfileResponseBody = new UserProfileResponseBody();
        userProfileResponseBody.setUser(findUserNoToken(fromUserId, toUserId));
        userProfileResponseBody.setPostList(postDao.findPost(toUserId));
        return userProfileResponseBody;
    }

    public MineResponseBody findUserInfo(String id) {
        User user = userDao.findUser(id);
        if (user != null) {
            user.setToken(null);
        }
        List<MapBean> list = new ArrayList<>();
        // 占位符
        list.add(null);
        list.add(new MapBean("分类", String.valueOf(kindDao.getKindCountByUserId(id))));
        list.add(new MapBean("卡片", String.valueOf(cardDao.getCardCountByUserId(id))));
        list.add(null);
        list.add(new MapBean("设置", String.valueOf(-1)));
        list.add(new MapBean("关于", String.valueOf(-1)));
        MineResponseBody mineResponseBody = new MineResponseBody();
        mineResponseBody.setUser(user);
        mineResponseBody.setList(list);
        return mineResponseBody;
    }

    public String updateAvatar(String userId, MultipartFile file) {
        String path = FileUtil.writeFile(rootPath, AVATAR_PATH, localHost, file);
        if (path != null) {
            if (userDao.updateAvatar(userId, path) == 1) {
                return "\"" + path + "\"";
            }
        }
        return null;
    }

    public List<User> findFollowList(String userId) {
        return userDao.findFollowList(userId);
    }

    public List<User> findFansList(String userId) {
        return userDao.findFansList(userId);
    }
}

