package com.miao.controller;

import com.miao.entity.SysUserEntity;
import com.miao.util.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    /**
     *
     * @return 跳转到登陆页面
     */
    @RequestMapping("/user/login_page")
    public String login_page(){
        return "login";
    }

    /**
     * 登陆
     * @param sysUserEntity 用户
     * @param model 返回给前端msg
     * @return
     */
    @PostMapping("/user/login")
    public String login(SysUserEntity sysUserEntity, Model model){

        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject = SecurityUtils.getSubject();

        //2.封装用户数据,并且把密码进行加密,然后比对
        String md5paassword = Md5Util.getMD5(sysUserEntity.getPassword(),sysUserEntity.getUsername());
        sysUserEntity.setPassword(md5paassword);

        UsernamePasswordToken token = new UsernamePasswordToken(sysUserEntity.getUsername(), sysUserEntity.getPassword());

        //3.执行登录方法
        try {
            subject.login(token);
            //登录成功
            //跳转到welcome.jsp
            return "redirect:/user/welcome";
        }catch (UnknownAccountException e){
            //e.printStackTrace();
            //登录失败: 用户名不存在
            model.addAttribute("msg", "用户名不存在或已注销");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
