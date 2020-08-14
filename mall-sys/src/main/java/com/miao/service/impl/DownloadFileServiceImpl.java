package com.miao.service.impl;

import com.miao.service.DownloadFileService;
import com.miao.utils.ZipUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@Component
public class DownloadFileServiceImpl implements DownloadFileService {

    @Override
    public void DownFile(HttpServletResponse response) {
        String filename = "images.zip";
        // 如果文件名不为空，则进行下载
        if (filename != null) {
            String filepath = "/E:/MallSystem/mall-sys/target/classes/static/images";
            File file = new File(filepath);
            // 如果文件存在，则进行下载
            if (file.exists()) {

                //压缩文件
                ZipUtil.compress(filepath,filepath);
                File zipFile = new File(filepath+".zip");

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                try {
                    response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(zipFile);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download  successfully!");

                } catch (Exception e) {
                    System.out.println("Download  failed!");

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
}
