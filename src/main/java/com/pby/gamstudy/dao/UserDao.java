package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao {

    @Insert("insert into user (id, head, nickName, time, token) values(#{id}, #{head}, #{nickName},#{time}, #{token})")
    int register(User user);

    @Select("select * from user where id = #{id}")
    User findUser(@Param("id") String id);
}
