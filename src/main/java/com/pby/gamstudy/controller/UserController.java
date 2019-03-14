package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.User;
import com.pby.gamstudy.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService mUserService;

    @PostMapping("/findUser")
    public User findUser(@Param("id") String id) {
        return mUserService.findUser(id);
    }
}
