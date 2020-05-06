package com.miao.controller;

import com.miao.entity.SysUserEntity;
import com.miao.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    //欢迎页面
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    //跳转到登陆页面
    @RequestMapping("login_page")
    public String login_page(){
        return "login";
    }

    //跳转到注册页面
    @RequestMapping("register_page")
    public String register_page(){
        return "register";
    }

    //登陆
    @PostMapping("login")
    public String login(){
        return "welcome";
    }

    @Autowired
    private SysUserService sysUserService;
    //注册
    @PostMapping("register")
    public String register(SysUserEntity sysUserEntity){
        boolean b = sysUserService.addUser(sysUserEntity);
        if (b) {
            return "login";
        }
        return "error";
    }

}
