package com.miao.controller;

import com.miao.entity.SysUserEntity;
import com.miao.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    private static Thread thread;
    /**
     *
     * @return 跳转到登陆页面
     */
    @RequestMapping("/user/login_page")
    public String login_page(){
        return "login";
    }


    @RequestMapping("/user/login")
    public String getLogin(){
        return "login";
    }

    /**
     * 登陆
     * @param sysUserEntity 用户
     * @param model 返回给前端msg
     * @return
     */
    @PostMapping("/user/login")
    public String login(SysUserEntity sysUserEntity, Model model, HttpSession session){
        return loginService.login(sysUserEntity,model,session);
    }
}
