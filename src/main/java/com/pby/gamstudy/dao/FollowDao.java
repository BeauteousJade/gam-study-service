package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.Follow;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FollowDao {
    @Insert("insert into follow(id, fromUserId, toUserId, time) select #{id}, #{fromUserId}, #{toUserId}, #{time} from dual" +
            " where not exists (select id from follow where fromUserId = #{fromUserId} and toUserId = #{toUserId})")
    int followUser(Follow follow);


    @Delete("delete from follow where fromUserId = #{fromUserId} and toUserId = #{toUserId}")
    int unFollowUser(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);

    @Select("select * from follow where toUserId = #{toUserId}")
    List<Follow> findAllFans(@Param("toUserId") String toUserId);

    @Select("select * from follow where fromUserId = #{fromUserId}")
    List<Follow> findAllFollower(@Param("fromUserId") String fromUserId);
}
