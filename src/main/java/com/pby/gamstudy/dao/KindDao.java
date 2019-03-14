package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.Kind;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface KindDao {

    @Select("select * from kind where userId = #{userId} order by recentBrowserTime limit 10")
    List<Kind> findRecentBrowseKind(@Param("userId") String userId);

    @Insert("insert into kind(id, userId, name, cover, time, count, recentBrowserTime)" +
            " select #{id}, #{userId}, #{name}, #{cover}, #{time}, #{count}, #{recentBrowserTime}" +
            " from dual where not exists (select id from kind where name = #{name} and userId = #{userId})")
    int insertKind(@Param("id") String id, @Param("userId") String userId,
                   @Param("name") String name, @Param("cover") String cover, @Param("time") long time,
                   @Param("count") int count, @Param("recentBrowserTime") long recentBrowserTime);

    @Select("select * from kind where userId = #{userId}")
    List<Kind> findAllKind(@Param("userId") String userId);

    @Update("update kind set recentBrowserTime = #{recentBrowserTime} where id = #{id}")
    int updateTime(@Param("id") String kindId, @Param("recentBrowserTime") long recentBrowserTime);

    @Update("update kind set count = count + 1  where id = #{id}")
    int increaseCount(@Param("id") String id);
}
