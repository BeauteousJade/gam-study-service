package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.Message;
import com.pby.gamstudy.bean.MessageItem;
import com.pby.gamstudy.service.IMService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/im")
public class IMController {

    @Autowired
    IMService imService;

    @PostMapping("/sendMessage")
    public boolean sendMessage(@RequestParam("message") String messageJson) {
        return imService.sendMessage(messageJson);
    }

    @PostMapping("/findMessageItem")
    public List<MessageItem> findMessageItem(@Param("userId") String userId) {
        return imService.findMessageItem(userId);
    }

    @PostMapping("/findHistoryMessage")
    public List<Message> findHistoryMessage(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId, @Param("startTime") long startTime, @Param("endTime") long endTime) {
        return imService.findHistoryMessage(fromUserId, toUserId, startTime, endTime);
    }

    @PostMapping("/resetFromUserUnReadCount")
    public boolean resetFromUserUnReadCount(@Param("id") String id) {
        return imService.resetFromUserUnReadCount(id);
    }


    @PostMapping("/resetToUserUnReadCount")
    public boolean resetToUserUnReadCount(@Param("id") String id) {
        return imService.resetToUserUnReadCount(id);
    }

    @PostMapping("/findSingleMessageItem")
    public MessageItem findSingleMessageItem(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId) {
        return imService.findSingleMessageItem(fromUserId, toUserId);
    }

    @PostMapping("/deleteMessageItemForFromUser")
    public boolean deleteMessageItemForFromUser(@Param("id") String id) {
        return imService.deleteMessageItemForFromUser(id);
    }

    @PostMapping("/deleteMessageItemForToUser")
    public boolean deleteMessageItemForToUser(@Param("id") String id) {
        return imService.deleteMessageItemForToUser(id);
    }
}
