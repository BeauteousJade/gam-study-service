package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.MessageItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MessageItemDao {

    @Insert("insert into message_item(id, fromUserId, toUserId, recentContent, recentTime, time) values(#{id}, #{fromUser.id}, #{toUser.id}, #{recentContent}, #{recentTime}, #{time})")
    int addOrUpdateMessageItem(MessageItem messageItem);


    @Select("select * from where message_item where fromUserId = #{fromUserId} and toUserId = #{toUserId}")
    MessageItem findMessageItemByUserId(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);

    @Update("update message_item set recentContent = #{recentContent}, recentTime = #{recentTime}")
    int updateMessageItem(@Param("recentContent") String recentContent, @Param("recentTime") long recentTime);
}
