package com.pby.gamstudy.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/im")
public class IMController {

    @PostMapping("/sendMessage")
    public boolean sendMessage(@Param("message") String messageJson) {
        return true;
    }

}
