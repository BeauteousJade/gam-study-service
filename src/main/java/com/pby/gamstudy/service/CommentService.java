package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.Comment;
import com.pby.gamstudy.dao.CommentDao;
import com.pby.gamstudy.util.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommentService {

    @Autowired
    CommentDao mCommentDao;

    public Comment addComment(String commentJson) {
        Comment comment = GsonUtil.jsonToObject(commentJson, Comment.class);
        if (comment == null) {
            return null;
        }
        comment.setTime(System.currentTimeMillis());
        if (mCommentDao.addComment(comment) == 1) {
            return comment;
        }
        return null;
    }
}
