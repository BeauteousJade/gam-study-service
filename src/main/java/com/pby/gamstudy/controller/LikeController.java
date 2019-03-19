package com.pby.gamstudy.controller;

import com.pby.gamstudy.service.LikeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeController {

    @Autowired
    LikeService mLikeService;

    @PostMapping("/like")
    public boolean like(@Param("userId") String userId, @Param("postId") String postId, @Param("isLike") boolean isLike) {
        return mLikeService.like(userId, postId, isLike);
    }
}
