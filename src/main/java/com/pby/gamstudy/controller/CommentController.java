package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.Comment;
import com.pby.gamstudy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {


    @Autowired
    CommentService mCommentService;


    @PostMapping("/addComment")
    public Comment addComment(@RequestParam("comment") String commentJson) {
        return mCommentService.addComment(commentJson);
    }
}
