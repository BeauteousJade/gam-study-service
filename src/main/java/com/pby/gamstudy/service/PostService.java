package com.pby.gamstudy.service;

import com.pby.gamstudy.bean.Post;
import com.pby.gamstudy.dao.PostDao;
import com.pby.gamstudy.util.ArraysUtil;
import com.pby.gamstudy.util.FileUtil;
import com.pby.gamstudy.util.StringUtil;
import com.pby.gamstudy.util.gson.GsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
        final Post post = GsonUtil.jsonToObject(postJson, Post.class);
        if (post != null) {
            List<String> imageUrlList = new ArrayList<>();
            if (!ArraysUtil.isEmpty(multipartFiles)) {
                for (MultipartFile file : multipartFiles) {
                    imageUrlList.add(FileUtil.writeFile(rootPath, SECOND_PATH, localHost, file));
                }
            }
            post.setId(StringUtil.generateId());
            post.setImageUrlList(imageUrlList);
            post.setTime(System.currentTimeMillis());
            if (mPostDao.insertPost(post) == 1) {
                return post;
            }
        }
        return null;
    }

    public List<Post> findPost(String userId) {
        return mPostDao.findPost(userId);
    }

    public List<Post> findRecommendPost() {
        return mPostDao.findRecommendPost();
    }

    public List<Post> findFollowPost(String userId) {
        return mPostDao.findFollowPost(userId);
    }
}
