package com.miao.service.impl;

import com.miao.entity.SysUserEntity;
import com.miao.service.LoginService;
import com.miao.utils.Md5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Component
public class LoginServiceImpl implements LoginService {

    @Override
    public String login(SysUserEntity sysUserEntity, Model model, HttpSession session) {

        if (session.getAttribute("login_error") != null) {

            //允许错误次数
            if ((int) session.getAttribute("login_error") == 5 ) {

                //当前时间减去最后一次错误输入的时间
                long wait_time = System.currentTimeMillis() - (long) session.getAttribute("login_error_time");
                wait_time = wait_time/1000;

                if (wait_time <= 59) {
                    model.addAttribute("msg", "尝试次数过多请" + (60 - wait_time) + "秒后再试");
                    return "login";
                } else {
                    //重新记录登录错误次数
                    session.setAttribute("login_error", 0);
                }
            }
        }

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
            return "redirect:/user/index";
        }catch (UnknownAccountException e){
            //e.printStackTrace();
            //登录失败: 用户名不存在
            model.addAttribute("msg", "用户名不存在或已注销");
            return "login";
        }catch (IncorrectCredentialsException e){

            //记录密码错误次数
            if (session.getAttribute("login_error") == null){
                session.setAttribute("login_error",1);
            }else{
                int login_error = (int) session.getAttribute("login_error");
                login_error++;
                session.setAttribute("login_error",login_error);
            }
            long currentTimeMillis = System.currentTimeMillis();
            session.setAttribute("login_error_time",currentTimeMillis);

            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
