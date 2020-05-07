package com.miao.controller;

import com.miao.entity.SysUserEntity;
import com.miao.service.SysUserService;
import com.miao.util.Md5Util;
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
    @RequestMapping("/user/index")
    public String index(){
        return "index";
    }

    /**
     *
     * @return 跳转到登陆页面
     */
    @RequestMapping("/user/login_page")
    public String login_page(){
        return "login";
    }

    /**
     *
     * @return 跳转到注册页面
     */
    @RequestMapping("/user/register_page")
    public String register_page(){
        return "register";
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

    /**
     * @return 跳转到会员页面
     */
    @RequestMapping("/user/vip")
    public String vip(){
        return "vip";
    }

    /**
     * @return 跳转到会员开通页面
     */
    @RequestMapping("/user/open_vip_page")
    public String open_vip_page(){
        return "openvip";
    }

    /**
     * 开通会员
     * @param
     * @return
     */
    @RequestMapping("/user/openvip")
    public String openvip(){
        sysUserService.openVip();
        return "vip";
    }

    /**
     * 注册
     * @param sysUserEntity
     * @return 成功跳转到登录页面
     * 失败跳转到错误页面
     */
    @PostMapping("/user/register")
    public String register(SysUserEntity sysUserEntity, Model model){
        if (sysUserEntity.getUsername() == ""){
            model.addAttribute("msg", "用户名不能为空");
            return "register";
        }
        boolean b = sysUserService.addUser(sysUserEntity);
        if (b) {
            return "login";
        }
        model.addAttribute("msg", "用户名已存在");
        return "register";
    }

    /**
     * 登出
     * @return 登出页面
     */
    @RequestMapping("/user/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "logout";
    }

    /**
     * 登陆
     * @param user 用户
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
