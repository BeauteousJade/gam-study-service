package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.Post;
import com.pby.gamstudy.service.PostService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    PostService mPostService;

    @PostMapping("/insertPost")
    public Post insertPost(@RequestParam("post") String postJson, @RequestParam("file") List<MultipartFile> multipartFiles) {
        return mPostService.insertPost(postJson, multipartFiles);
    }

    @PostMapping("/findRecommendPost")
    public List<Post> findRecommendPost() {
        return mPostService.findRecommendPost();
    }

    @PostMapping("/findFollowPost")
    public List<Post> findFollowPost(@Param("userId") String userId) {
        return mPostService.findFollowPost(userId);
    }
}
