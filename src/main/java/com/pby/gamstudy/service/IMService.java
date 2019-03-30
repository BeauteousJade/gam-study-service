package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.IMMessage;
import com.pby.gamstudy.dao.IMDao;
import com.pby.gamstudy.util.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class IMService {

    @Autowired
    IMDao imDao;

    public boolean sendMessage(String message) {
        IMMessage imMesssage = GsonUtil.jsonToObject(message, IMMessage.class);
        if (imMesssage != null) {
            return imDao.sendMessage(imMesssage);
        }
        return false;
    }
}
