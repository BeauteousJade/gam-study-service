package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.Post;
import com.pby.gamstudy.dao.PostDao;
import com.pby.gamstudy.util.ArraysUtil;
import com.pby.gamstudy.util.FileUtil;
import com.pby.gamstudy.util.JsonUtil;
import com.pby.gamstudy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@Repository
public class PostService {

    private static final String SECOND_PATH = "post";

    @Value("${file.rootPath}")
    String rootPath;
    @Value("${localHost}")
    String localHost;

    @Autowired
    PostDao mPostDao;

    public Post insertPost(String postJson, List<MultipartFile> multipartFiles) {
        final Post post = JsonUtil.jsonToObjecr(postJson, Post.class);
        if (post != null) {
            List<String> imageUrlList = new ArrayList<>();
            if (!ArraysUtil.isEmpty(multipartFiles)) {
                for (MultipartFile file : multipartFiles) {
                    imageUrlList.add(FileUtil.writeFile(rootPath, localHost, SECOND_PATH, file));
                }
            }
            post.setId(StringUtil.generateId());
            post.setImageUrlList(imageUrlList);
            if (mPostDao.insertPost(post) == 1) {
                return post;
            }
        }
        return null;
    }
}
