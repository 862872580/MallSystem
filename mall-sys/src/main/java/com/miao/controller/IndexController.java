package com.miao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    /**
     *
     * @return 初始页面
     */
    @RequestMapping("/user/index")
    public String index(){
        return "index";
    }

    /**
     * @return 跳转到未授权页面
     */
    @RequestMapping("/user/noAuth")
    public String noAuth(){
        return "noAuth";
    }

    /**
     * @return 跳转到欢迎页面
     */
    @RequestMapping("/user/welcome")
    public String welcome(){
        return "welcome";
    }





}
