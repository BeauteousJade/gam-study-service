package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @Insert("insert into user (id, head, nickName, time, token) values(#{id}, #{head}, #{nickName},#{time}, #{token})")
    int register(User user);

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "fansUserList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.FollowDao.findAllFans")),
            @Result(property = "followUserList", column = "id", many = @Many(select = "com.pby.gamstudy.dao.FollowDao.findAllFollower"))
    })
    User findUser(@Param("id") String id);

    @Select("select * from user where id = #{id}")
    User findBasicUser(@Param("id") String id);
}
