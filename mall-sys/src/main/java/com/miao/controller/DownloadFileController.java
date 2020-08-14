package com.miao.controller;

import com.miao.service.DownloadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class DownloadFileController {

    @Autowired
    DownloadFileService downloadFileService;

    /**
     *
     * @return 下载压缩文件页面
     */
    @RequestMapping("/user/download_page")
    public String download_page(){
        return "download";
    }

    /**
     *
     * @param response 下载文件
     */
    @RequestMapping("/user/download_zip")
    public void download_zip(HttpServletResponse response){
        downloadFileService.DownFile(response);
    }
}
