package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.Card;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CardDao {

    @Insert("insert into card(id, userId, kindId, oldImageUrl, editImageUrl, time, answer) values(#{id}, #{userId}, #{kindId}, #{oldImageUrl}, #{editImageUrl}, #{time}, #{answer})")
    int insertKind(Card card);

    @Select("select * from card where userId = #{userId} and kindId = #{kindId}")
    List<Card> findAllCard(@Param("userId") String userId, @Param("kindId") String kindId);

    @Update("update card set editImageUrl  = #{editImageUrl}, answer = #{answer}, updateTime = #{updateTime} where id = #{id}")
    int editCard(Card card);

    @Select("select * from card where userId = #{userId} limit 50")
    List<Card> findDailyCard(@Param("userId") String userId);
}
