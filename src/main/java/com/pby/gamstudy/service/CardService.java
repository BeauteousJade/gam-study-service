package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.Card;
import com.pby.gamstudy.dao.CardDao;
import com.pby.gamstudy.dao.KindDao;
import com.pby.gamstudy.util.ArraysUtil;
import com.pby.gamstudy.util.FileUtil;
import com.pby.gamstudy.util.JsonUtil;
import com.pby.gamstudy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public class CardService {

    private static final String CARD_PATH = "card";

    @Value("${file.rootPath}")
    String rootPath;
    @Value("${localHost}")
    String localHost;

    @Autowired
    CardDao mCardDao;
    @Autowired
    KindDao mKindDao;

    public Card insertCard(String userId, String kindId, String answer, List<MultipartFile> files) {
        if (ArraysUtil.isEmpty(files) || files.size() != 2) {
            return null;
        }
        String oldImageUrl = FileUtil.writeFile(rootPath, CARD_PATH, localHost, files.get(0));
        String editImageUrl = FileUtil.writeFile(rootPath, CARD_PATH, localHost, files.get(1));
        if (oldImageUrl != null && editImageUrl != null) {
            Card card = new Card();
            card.setId(StringUtil.generateId());
            card.setKindId(kindId);
            card.setUserId(userId);
            card.setOldImageUrl(oldImageUrl);
            card.setEditImageUrl(editImageUrl);
            card.setAnswer(answer);
            card.setTime(System.currentTimeMillis());
            card.setUpdateTime(System.currentTimeMillis());
            mCardDao.insertKind(card);
            mKindDao.increaseCount(card.getId());
            return card;
        }
        return null;
    }

    public List<Card> findAllCard(String userId, String kindId) {
        return mCardDao.findAllCard(userId, kindId);
    }


    public Card editCard(String cardJson, MultipartFile file) {
        final Card card = JsonUtil.jsonToObjecr(cardJson, Card.class);
        if (card == null) {
            return null;
        }
        String newEdiImageUrl = FileUtil.writeFile(rootPath, CARD_PATH, localHost, file);
        if (newEdiImageUrl != null) {
            card.setEditImageUrl(newEdiImageUrl);
            card.setUpdateTime(System.currentTimeMillis());
            if (mCardDao.editCard(card) == 1) {
                return card;
            }
        }
        return null;
    }


}
