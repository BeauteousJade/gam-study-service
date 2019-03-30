package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.Follow;
import com.pby.gamstudy.dao.FollowDao;
import com.pby.gamstudy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class FollowService {

    @Autowired
    FollowDao followDao;

    public boolean followUser(String fromUserId, String toUserId) {
        Follow follow = new Follow();
        follow.setId(StringUtil.generateId());
        follow.setFromUserId(fromUserId);
        follow.setToUserId(toUserId);
        follow.setTime(System.currentTimeMillis());
        return followDao.followUser(follow) == 1;
    }

    public boolean unFollowUser(String fromUserId, String toUserId) {
        return followDao.unFollowUser(fromUserId, toUserId) == 1;
    }
}
