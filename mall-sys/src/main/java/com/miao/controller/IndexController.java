package com.miao.controller;

import com.miao.entity.SysUserEntity;
import com.miao.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private SysUserService sysUserService;

    /**
     *
     * @return 初始页面
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }

    /**
     *
     * @return 跳转到登陆页面
     */
    @RequestMapping("login_page")
    public String login_page(){
        return "login";
    }

    /**
     *
     * @return 跳转到注册页面
     */
    @RequestMapping("register_page")
    public String register_page(){
        return "register";
    }

    /**
     * @return 跳转到欢迎页面
     */
    @RequestMapping("welcome")
    public String welcome(){
        return "welcome";
    }

    /**
     * 注册
     * @param sysUserEntity
     * @return 成功跳转到登录页面
     * 失败跳转到错误页面
     */
    @PostMapping("register")
    public String register(SysUserEntity sysUserEntity){
        boolean b = sysUserService.addUser(sysUserEntity);
        if (b) {
            return "login";
        }
        return "error";
    }

    //登出
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout";
    }

    //登陆
    @PostMapping("login")
    public String login(SysUserEntity user, Model model){

        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());

        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            //跳转到welcome.jsp
            return "redirect:welcome";
        }catch (UnknownAccountException e){
            //e.printStackTrace();
            //登录失败: 用户名不存在
            model.addAttribute("msg", "用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }

}
