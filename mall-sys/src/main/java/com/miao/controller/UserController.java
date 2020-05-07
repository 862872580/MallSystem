package com.miao.controller;

import com.miao.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @Autowired
    private SysUserService sysUserService;
    /**
     * @return 跳转到修改密码页面
     */
    @RequestMapping("/user/change_password_page")
    public String change_password_page(){
        return "changepassword";
    }

    @PostMapping("/user/changepassword")
    public String changepassword(String password, String newpassword_1, String newpassword_2){

        //对比第一二次输入的新密码
        if (!newpassword_1.equals(newpassword_2)){
            return "changepassword";
        }
        //修改成功
        boolean b = sysUserService.changePassword(password, newpassword_1);
        if (b){
            return "login";
        }
        //修改失败返回修改密码页面
        return "changepassword";
    }
}
