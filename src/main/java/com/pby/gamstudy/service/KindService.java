package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.Kind;
import com.pby.gamstudy.dao.KindDao;
import com.pby.gamstudy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class KindService {

    @Autowired
    KindDao mKindDao;

    public List<Kind> findRecentBrowseKind(String userId) {
        return mKindDao.findRecentBrowseKind(userId);
    }

    public int insertKind(String userId, String cover, String name) {
        final String id = StringUtil.generateId();
        final long time = System.nanoTime();
        return mKindDao.insertKind(id, userId, name, cover, time, 0, time);
    }

    public List<Kind> findAllKind(String userId) {
        return mKindDao.findAllKind(userId);
    }

    public boolean updateTime(String kindId) {
        final long recentBrowserTime = System.nanoTime();
        return mKindDao.updateTime(kindId, recentBrowserTime) == 1;
    }
}
