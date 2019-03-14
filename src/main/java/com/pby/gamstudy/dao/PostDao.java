package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostDao {

    @Insert("insert into post(id, userId, content, imageUrl, time) values(#{id}, #{user#{id}}, #{content}, #{imageUrlList})")
    int insertPost(@Param("post") Post post);
}
