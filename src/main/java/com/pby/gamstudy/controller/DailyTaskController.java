package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.DailyTask;
import com.pby.gamstudy.service.DailyTaskService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/daily")
public class DailyTaskController {

    @Autowired
    DailyTaskService mDailyTaskService;

    @PostMapping("/findDailyTask")
    public List<DailyTask> findDailyTask(@Param("userId") String userId) {
        return mDailyTaskService.findDailyTask(userId);
    }

    @PostMapping("/sign")
    public boolean sign(@Param("userId") String userId) {
        return mDailyTaskService.sign(userId);
    }

    @PostMapping("/updateDailyCard")
    public boolean updateDailyCard(@Param("userId") String userId, @Param("cardId") String cardId) {
        return mDailyTaskService.updateDailyCard(userId, cardId);
    }
}
