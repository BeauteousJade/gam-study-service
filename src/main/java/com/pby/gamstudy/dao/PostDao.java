package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.Post;
import com.pby.gamstudy.handler.ListTypeHandler;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PostDao {

    @Insert("insert into post(id, userId, content, imageUrlList, time) values(#{id}, #{user.id}, #{content}, #{imageUrlList,typeHandler=com.pby.gamstudy.handler.ListTypeHandler}, #{time})")
    int insertPost(Post post);

    @Select("select * from post where userId = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "user", column = "userId", one = @One(select = "com.pby.gamstudy.dao.UserDao.findUser")),
            @Result(property = "commentList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.CommentDao.findCommentByPostId")),
            @Result(property = "likeUserList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.EnjoyDao.findLikeUserByPostId")),
            @Result(property = "imageUrlList", column = "imageUrlList", typeHandler = ListTypeHandler.class)
    })
    List<Post> findPost(@Param("userId") String userId);

    @Select("select *  from post order by time DESC limit 100")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "user", column = "userId", one = @One(select = "com.pby.gamstudy.dao.UserDao.findUser")),
            @Result(property = "commentList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.CommentDao.findCommentByPostId")),
            @Result(property = "likeUserList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.EnjoyDao.findLikeUserByPostId")),
            @Result(property = "imageUrlList", column = "imageUrlList", typeHandler = ListTypeHandler.class)
    })
    List<Post> findRecommendPost();

    @Select("select * from post where userId = #{userId} or userId in (select fromUserId from follow where toUserId = #{userId}) order by time DESC")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "user", column = "userId", one = @One(select = "com.pby.gamstudy.dao.UserDao.findUser")),
            @Result(property = "commentList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.CommentDao.findCommentByPostId")),
            @Result(property = "likeUserList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.EnjoyDao.findLikeUserByPostId")),
            @Result(property = "imageUrlList", column = "imageUrlList", typeHandler = ListTypeHandler.class)
    })
    List<Post> findFollowPost(@Param("userId") String userId);

}
