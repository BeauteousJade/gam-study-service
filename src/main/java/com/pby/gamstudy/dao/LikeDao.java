package com.pby.gamstudy.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LikeDao {

    @Insert("insert into `like`(id, userId, postId, time) values(#{id}, #{userId}, #{postId}, #{time})")
    int insertLike(@Param("id") String id, @Param("userId") String userId, @Param("postId") String postId, @Param("time") long time);

    @Delete("delete from `like` where userId = #{userId} and postId = #{postId} limit 1")
    int deleteLike(@Param("userId") String userId, @Param("postId") String postId);
}
