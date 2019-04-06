package com.pby.gamstudy.service;

import com.pby.gamstudy.util.ArraysUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
@Repository
public class ApkService {

    private static final String APK_PATH = "apk";

    @Value("${file.rootPath}")
    String rootPath;
    @Value("${localHost}")
    String localHost;

    public String checkUpdate(String code) {
        final String fileName = code + ".apk";
        File file = new File(rootPath + File.separator + APK_PATH);
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            if (!ArraysUtil.isEmpty(files)) {
                for (File f : files) {
                    if (f.getName().compareTo(fileName) > 0) {
                        return "\"" + localHost + File.separator + APK_PATH + File.separator + f.getName() + "\"";
                    }
                }
            }
        }
        return null;
    }

    public void downloadApk(String url, HttpServletResponse response) {
        String fileName = getFileName(url);
        if (fileName != null) {
            File file = new File(rootPath + File.separator + APK_PATH, fileName);
            if (file.exists()) {
                response.setContentType("application/octet-stream");//
                response.setHeader("content-type", "application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                response.setContentLengthLong(file.length());
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private String getFileName(String path) {
        int index = path.lastIndexOf(File.separator);
        if (index < path.length()) {
            return path.substring(index + 1);
        }
        return null;
    }
}
