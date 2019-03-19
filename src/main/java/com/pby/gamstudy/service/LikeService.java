package com.pby.gamstudy.service;

import com.pby.gamstudy.dao.LikeDao;
import com.pby.gamstudy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class LikeService {

    @Autowired
    LikeDao mLikeDao;

    public boolean like(String userId, String postId, boolean isLike) {
        if (isLike) {
            return mLikeDao.insertLike(StringUtil.generateId(), userId, postId, System.currentTimeMillis()) == 1;
        } else {
            return mLikeDao.deleteLike(userId, postId) == 1;
        }
    }
}
