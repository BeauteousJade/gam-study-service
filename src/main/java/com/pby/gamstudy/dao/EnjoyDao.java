package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EnjoyDao {
    @Select("select * from user where id in (select userId from `like` where postId = #{postId})")
    List<User> findLikeUserByPostId(@Param("postId") String id);
}
