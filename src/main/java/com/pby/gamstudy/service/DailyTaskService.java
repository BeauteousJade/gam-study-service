package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.Card;
import com.pby.gamstudy.bean.DailyTask;
import com.pby.gamstudy.configuration.redis.RedisManager;
import com.pby.gamstudy.dao.CardDao;
import com.pby.gamstudy.dao.UserDao;
import com.pby.gamstudy.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class DailyTaskService {
    private static final String DAILY_CARD_SUFFIX = "daily_card_suffix";
    private static final String DAILY_SIGN_SUFFIX = "daily_sign_suffix";
    private static final String DAILY_CARD_COUNT_SUFFIX = "daily_card_count_suffix";

    @Autowired
    CardDao mCardDao;
    @Autowired
    UserDao userDao;
    @Autowired
    RedisManager mRedisManager;

    public List<DailyTask> findDailyTask(String userId) {
        final String cardKey = userId + DAILY_CARD_SUFFIX;
        final String signKey = userId + DAILY_SIGN_SUFFIX;
        final String countKey = userId + DAILY_CARD_COUNT_SUFFIX;
        final List<DailyTask> dailyTaskList = new ArrayList<>();
        List<Card> dailyCardList = mRedisManager.get(cardKey);
        int dailyCardCount = dailyCardList == null ? 0 : mRedisManager.get(userId + DAILY_CARD_COUNT_SUFFIX);
        boolean isSign = mRedisManager.get(signKey) != null;
        if (dailyCardList == null) {
            dailyCardList = mCardDao.findDailyCard(userId);
            dailyCardCount = dailyCardList.size();
            mRedisManager.set(cardKey, dailyCardList, TimeUtil.getOffsetTimeForNextDay(), TimeUnit.MILLISECONDS);
            mRedisManager.set(countKey, dailyCardCount, TimeUtil.getOffsetTimeForNextDay(), TimeUnit.MILLISECONDS);
        }
        dailyTaskList.add(new DailyTask(isSign, null, 0));
        dailyTaskList.add(new DailyTask(false, dailyCardList, dailyCardCount));
        return dailyTaskList;
    }

    public boolean sign(String userId) {
        mRedisManager.set(userId + DAILY_SIGN_SUFFIX, true, TimeUtil.getOffsetTimeForNextDay(), TimeUnit.MILLISECONDS);
        return userDao.increaseScore(userId) == 1;
    }

    public boolean updateDailyCard(String userId, String cardId) {
        final String key = userId + DAILY_CARD_SUFFIX;
        final List<Card> dailyCardList = mRedisManager.get(key);
        if (dailyCardList != null) {
            for (int i = dailyCardList.size() - 1; i >= 0; i--) {
                if (dailyCardList.get(i).getId().equals(cardId)) {
                    dailyCardList.remove(i);
                    mRedisManager.set(key, dailyCardList, TimeUtil.getOffsetTimeForNextDay(), TimeUnit.MILLISECONDS);
                    return true;
                }
            }
        }
        return false;
    }
}
