package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.Follow;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FollowDao {

    @Insert("insert into follow(id, fromUserId, toUserId, time) values(#{id}, #{fromUserId}, #{toUserId})")
    int followUser(Follow follow);
}
