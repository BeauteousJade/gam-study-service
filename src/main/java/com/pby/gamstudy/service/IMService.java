package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.IMMessage;
import com.pby.gamstudy.bean.Message;
import com.pby.gamstudy.bean.MessageItem;
import com.pby.gamstudy.bean.User;
import com.pby.gamstudy.dao.IMDao;
import com.pby.gamstudy.dao.MessageItemDao;
import com.pby.gamstudy.util.StringUtil;
import com.pby.gamstudy.util.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Repository
public class IMService {

    @Autowired
    IMDao imDao;
    @Autowired
    MessageItemDao messageItemDao;

    public boolean sendMessage(String message) {
        IMMessage imMessage = GsonUtil.jsonToObject(message, IMMessage.class);
        if (imMessage != null) {
            if (imDao.sendMessage(imMessage)) {
                if (messageItemDao.findMessageItemByUserId(imMessage.getFromUserId(), imMessage.getToUserId()) == null) {
                    MessageItem messageItem = new MessageItem();
                    messageItem.setId(StringUtil.generateId());
                    messageItem.setFromUser(new User(imMessage.getFromUserId()));
                    messageItem.setToUser(new User(imMessage.getToUserId()));
                    messageItem.setRecentContent(imMessage.getContent());
                    messageItem.setRecentTime(System.currentTimeMillis());
                    messageItem.setTime(System.currentTimeMillis());
                    return messageItemDao.addOrUpdateMessageItem(messageItem) == 1;
                } else {
                    return messageItemDao.updateMessageItem(imMessage.getContent(), System.currentTimeMillis()) == 1;
                }
            }
        }
        return false;
    }

    public List<MessageItem> findMessageItem(String userId) {
        return messageItemDao.findMessageItem(userId);
    }

    public List<Message> findHistoryMessage(String fromUserId, String toUserId) {

    }
}
