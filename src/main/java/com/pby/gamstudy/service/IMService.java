package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.Message;
import com.pby.gamstudy.bean.MessageItem;
import com.pby.gamstudy.bean.User;
import com.pby.gamstudy.dao.IMDao;
import com.pby.gamstudy.dao.MessageItemDao;
import com.pby.gamstudy.dao.UserDao;
import com.pby.gamstudy.util.StringUtil;
import com.pby.gamstudy.util.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Repository
public class IMService {

    @Autowired
    IMDao imDao;
    @Autowired
    MessageItemDao messageItemDao;
    @Autowired
    UserDao userDao;

    public boolean sendMessage(String messageJson) {
        Message message = GsonUtil.jsonToObject(messageJson, Message.class);
        if (message != null) {
            if (imDao.sendMessage(message)) {
                MessageItem messageItem;
                if ((messageItem = messageItemDao.findMessageItemByUserId(message.getFromUser().getId(), message.getToUser().getId())) == null) {
                    messageItem = new MessageItem();
                    messageItem.setId(StringUtil.generateId());
                    messageItem.setFromUser(new User(message.getFromUser().getId()));
                    messageItem.setToUser(new User(message.getToUser().getId()));
                    messageItem.setRecentContent(message.getContent());
                    messageItem.setRecentSendUserId(message.getSendUserId());
                    messageItem.setRecentTime(System.currentTimeMillis());
                    messageItem.setToUserUnReadCount(1);
                    messageItem.setTime(System.currentTimeMillis());
                    return messageItemDao.addOrUpdateMessageItem(messageItem) == 1;
                } else {
                    if (Objects.equals(message.getSendUserId(), messageItem.getFromUser().getId())) {
                        messageItem.setToUserUnReadCount(messageItem.getToUserUnReadCount() + 1);
                        return messageItemDao.updateMessageItemForToUser(message.getContent(), System.currentTimeMillis()
                                , messageItem.getId(), message.getSendUserId(), messageItem.getToUserUnReadCount()) > 0;
                    } else {
                        messageItem.setFromUserUnReadCount(messageItem.getFromUserUnReadCount() + 1);
                        return messageItemDao.updateMessageItemForFromUser(message.getContent(), System.currentTimeMillis()
                                , messageItem.getId(), message.getSendUserId(), messageItem.getFromUserUnReadCount()) > 0;
                    }
                }
            }
        }
        return false;
    }

    public List<MessageItem> findMessageItem(String userId) {
        return messageItemDao.findMessageItem(userId);
    }

    public List<Message> findHistoryMessage(String fromUserId, String toUserId, long startTime, long endTime) {
        List<Message> result = new ArrayList<>();
        User fromUser = userDao.findBasicUser(fromUserId);
        User toUser = userDao.findBasicUser(toUserId);
        result.addAll(getHistoryMessage(fromUser, toUser, imDao.findHistoryMessage(fromUserId, toUserId, startTime, endTime)));
        return result;
    }

    private List<Message> getHistoryMessage(User fromUser, User toUser, List<Message> historyMessage) {
        if (historyMessage != null) {
            for (Message message : historyMessage) {
                if (Objects.equals(message.getSendUserId(), fromUser.getId().toLowerCase())) {
                    message.setFromUser(fromUser);
                    message.setToUser(toUser);
                } else {
                    message.setFromUser(toUser);
                    message.setToUser(fromUser);
                }
            }
            return historyMessage;
        }
        return new ArrayList<>();
    }

    public boolean resetFromUserUnReadCount(String id) {
        return messageItemDao.resetFromUserUnReadCount(id) == 1;
    }

    public boolean resetToUserUnReadCount(String id) {
        return messageItemDao.resetToUserUnReadCount(id) == 1;
    }

    public MessageItem findSingleMessageItem(String fromUserId, String toUserId) {
        return messageItemDao.findMessageItemByUserId(fromUserId, toUserId);
    }

    public boolean deleteMessageItemForFromUser(String id) {
        return messageItemDao.deleteMessageItemForFromUser(id) == 1;
    }


    public boolean deleteMessageItemForToUser(String id) {
        return messageItemDao.deleteMessageItemForToUser(id) == 1;
    }
}
