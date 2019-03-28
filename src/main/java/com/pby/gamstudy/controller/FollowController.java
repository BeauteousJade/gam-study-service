package com.pby.gamstudy.controller;

import com.pby.gamstudy.service.FollowService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/follow")
public class FollowController {

    @Autowired
    FollowService followService;

    @PostMapping("/followUser")
    public boolean followUser(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId) {
        return followService.followUser(fromUserId, toUserId);
    }
}