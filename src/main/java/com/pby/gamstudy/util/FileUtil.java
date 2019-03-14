package com.pby.gamstudy.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtil {

    private static final String SEPARATOR = "/";

    public static String writeFile(String rootPath, String secondPath, String localHost, MultipartFile multipartFile) {
        final String basePath = rootPath + File.separator + secondPath + File.separator;
        if (multipartFile.getOriginalFilename() != null) {
            final String prefix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
            final File file = new File(basePath + StringUtil.generateId() + prefix);
            try {
                if (!file.exists()) {
                    file.createNewFile();
                }
                multipartFile.transferTo(file);
                return localHost + SEPARATOR + secondPath + SEPARATOR + file.getName();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
