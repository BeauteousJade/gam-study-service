package com.pby.gamstudy.controller;

import com.pby.gamstudy.bean.Card;
import com.pby.gamstudy.service.CardService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService mCardService;

    @PostMapping("/insertCard")
    public Card insertCard(@Param("userId") String userId, @Param("kindId") String kindId, @Param("answer") String answer, @RequestParam("file") List<MultipartFile> files) {
        return mCardService.insertCard(userId, kindId, answer, files);
    }

    @PostMapping("/findAllCard")
    public List<Card> findAllCard(@Param("userId") String userId, @Param("kindId") String kindId) {
        return mCardService.findAllCard(userId, kindId);
    }

    @PostMapping("/editCard")
    public Card editCard(@RequestParam("card") String cardJson, @RequestParam("file") MultipartFile file) {
        return mCardService.editCard(cardJson, file);
    }
}
