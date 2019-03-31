package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.Message;
import com.pby.gamstudy.bean.MessageItem;
import com.pby.gamstudy.service.IMService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/im")
public class IMController {

    @Autowired
    IMService imService;

    @PostMapping("/sendMessage")
    public boolean sendMessage(@Param("message") String messageJson) {
        return true;
    }

    @PostMapping("/findMessageItem")
    public List<MessageItem> findMessageItem(@Param("userId") String userId) {
        return imService.findMessageItem(userId);
    }

    public List<Message> findHistoryMessage(@Param("fromUserId") String fromUserId, @Param("toUserId") String toUserId) {
        return null;
    }
}
