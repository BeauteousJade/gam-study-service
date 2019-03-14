package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.Cover;
import com.pby.gamstudy.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cover")
public class CoverController {

    @Autowired
    CoverService mCoverService;

    @PostMapping("/findAllCover")
    public List<Cover> findAllCover() {
        return mCoverService.findAllCover();
    }
}
