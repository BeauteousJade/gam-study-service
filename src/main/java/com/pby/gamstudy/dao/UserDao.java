package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDao {

    @Insert("insert into user (id, head, nickName,score, time, token) values(#{id}, #{head}, #{nickName}, #{score}, #{time}, #{token})")
    int register(User user);

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fansUserList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.FollowDao.findAllFans")),
            @Result(property = "followUserList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.FollowDao.findAllFollower"))
    })
    User findUser(@Param("id") String id);

    @Select("select *, (select count(1) from follow where fromUserId = #{fromUserId} and toUserId = user.id) as isFollow from user where id = #{toUserId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fansUserList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.FollowDao.findAllFans")),
            @Result(property = "followUserList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.FollowDao.findAllFollower"))
    })
    User findUserWithFollow(@Param("fromUserId") String formUserId, @Param("toUserId") String toUserId);

    @Select("select * from user where id = #{id}")
    User findBasicUser(@Param("id") String id);

    @Update("update user set score = score + #{score} where id = #{id}")
    int increaseScore(@Param("id") String id, @Param("score") int score);

    @Update("update user set head = #{head} where id = #{id}")
    int updateAvatar(@Param("id") String id, @Param("head") String head);

    @Select("select *, (1) as isFollow from user where id in" +
            " (select toUserId from follow where fromUserId = #{userId})")
    List<User> findFollowList(@Param("userId") String userId);

    @Select("select *, (select count(1) from follow where fromUserId = #{userId} and toUserId = user.id) as isFollow from user where id in" +
            " (select fromUserId from follow where toUserId = #{userId})")
    List<User> findFansList(@Param("userId") String userId);

    @Update("update user set nickName = #{userName} where id = #{id}")
    int modifyUserName(@Param("userName") String userName, @Param("id") String id);
}
