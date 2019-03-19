package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentDao {

    @Select("select * from comment where postId = #{postId} order by time")
    @Results({
            @Result(property = "toUser", column = "toUserId", one = @One(select = "com.pby.gamstudy.dao.UserDao.findUser")),
            @Result(property = "fromUser", column = "fromUserId", one = @One(select = "com.pby.gamstudy.dao.UserDao.findUser"))
    })
    List<Comment> findCommentByPostId(@Param("postId") String postId);

    @Insert("insert into comment(id, postId, fromUserId, toUserId, content,  time) values(#{id}, #{postId}, #{fromUser.id}, #{toUser.id}, #{content}, #{time})")
    int addComment(Comment comment);
}
