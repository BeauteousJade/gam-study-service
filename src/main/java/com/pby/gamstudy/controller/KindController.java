package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.Kind;
import com.pby.gamstudy.service.KindService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kind")
public class KindController {

    @Autowired
    KindService mKindService;

    @PostMapping("/findRecentBrowseKind")
    public List<Kind> findRecentBrowseKind(@Param("userId") String userId) {
        return mKindService.findRecentBrowseKind(userId);
    }

    @PostMapping("/insertKind")
    public int insertKind(@Param("userId") String userId, @Param("cover") String cover, @Param("name") String name) {
        return mKindService.insertKind(userId, cover, name);
    }

    @PostMapping("/findAllKind")
    public List<Kind> finAllKind(@Param("userId") String userId) {
        return mKindService.findAllKind(userId);
    }

    @PostMapping("/updateTime")
    public boolean updateTime(@Param("kindId") String kindId) {
        return mKindService.updateTime(kindId);
    }
}
