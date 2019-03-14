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

    @Insert("insert into user (id, head, nickName, time) select #{id}, #{head}, #{nickName},#{time} from dual where not exists (select id from user where id = #{id})")
    int register(@Param("id") String id, @Param("head") String head, @Param("nickName") String nickName, @Param("time") long time);

    @Select("select * from user where id = #{id}")
    User findUser(@Param("id") String id);
}
