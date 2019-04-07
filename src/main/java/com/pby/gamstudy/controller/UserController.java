package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.User;
import com.pby.gamstudy.bean.body.MineResponseBody;
import com.pby.gamstudy.bean.body.UserProfileResponseBody;
import com.pby.gamstudy.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService mUserService;

    @PostMapping("/findUser")
    public User findUser(@Param("id") String id) {
        return mUserService.findUser(id);
    }

    @PostMapping("/profile")
    public UserProfileResponseBody getUserProfile(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId) {
        return mUserService.getUserProfile(fromUserId, toUserId);
    }

    @PostMapping("/mine")
    public MineResponseBody findUserInfo(@Param("id") String id) {
        return mUserService.findUserInfo(id);
    }

    @PostMapping("/updateAvatar")
    public String updateAvatar(@Param("userId") String userId, @RequestParam("file") MultipartFile file) {
        return mUserService.updateAvatar(userId, file);
    }

    @PostMapping("/findFollowList")
    public List<User> findFollowList(@Param("userId") String userId) {
        return mUserService.findFollowList(userId);
    }

    @PostMapping("/findFansList")
    public List<User> findFansList(@Param("userId") String userId) {
        return mUserService.findFansList(userId);
    }

    @PostMapping("/modifyUserName")
    public boolean modifyUserName(@Param("userId") String userId, @Param("userName") String userName) {
        return mUserService.modifyUserName(userId, userName);
    }
}
