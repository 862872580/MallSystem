package com.miao.controller;

import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@Controller
public class TestController {

    /**
     *
     * @return 初始页面
     */
    @Test
    @RequestMapping("/user/test")
    public String test(){
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath()+"static/images";
        System.out.println(path);
        String fileName = "123.txt";
        File file = new File(path, fileName);
        return "test";
    }

}
