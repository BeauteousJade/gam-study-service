package com.pby.gamstudy.dao;

import com.pby.gamstudy.bean.MessageItem;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MessageItemDao {

    @Insert("insert into message_item(id, fromUserId, toUserId, recentContent, recentTime, time) values(#{id}, #{fromUser.id}, #{toUser.id}, #{recentContent}, #{recentTime}, #{time})")
    int addOrUpdateMessageItem(MessageItem messageItem);


    @Select("select * from message_item where fromUserId = #{fromUserId} and toUserId = #{toUserId}")
    MessageItem findMessageItemByUserId(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId);

    @Update("update message_item set recentContent = #{recentContent}, recentTime = #{recentTime}")
    int updateMessageItem(@Param("recentContent") String recentContent, @Param("recentTime") long recentTime);

    @Select("select * from message_item where fromUserId = #{userId} or toUserId = #{userId}")
    @Results({
            @Result(property = "fromUser", column = "fromUserId", one = @One(select = "com.pby.gamstudy.dao.UserDao.findBasicUser")),
            @Result(property = "toUser", column = "toUserId", one = @One(select = "com.pby.gamstudy.dao.UserDao.findBasicUser"))

    })
    List<MessageItem> findMessageItem(@Param("userId") String userId);
}
