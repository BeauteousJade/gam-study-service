package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.Cover;
import com.pby.gamstudy.dao.CoverDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CoverService {

    @Autowired
    CoverDao mCoverDao;

    public List<Cover> findAllCover() {
        return mCoverDao.findAllCover();
    }

}
