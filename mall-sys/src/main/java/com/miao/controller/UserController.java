package com.miao.controller;

import com.miao.entity.SysUserEntity;
import com.miao.entity.SysUserdataEntity;
import com.miao.service.SysUserService;
import com.miao.service.SysUserdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.apache.shiro.SecurityUtils.getSubject;

@Controller
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserdataService sysUserdataService;
    /**
     * @return 跳转到修改密码页面
     */
    @RequestMapping("/user/change_password_page")
    public String change_password_page(){
        return "changepassword";
    }

    /**
     * 测试
     * @return
     */
    @ResponseBody
    @RequestMapping("/user/findUser")
    public SysUserEntity findUser(){
        return sysUserService.findByName("miao");
    }

    /**
     * 修改密码
     * @param password  原密码
     * @param newpassword_1 新密码
     * @param newpassword_2 确认密码
     * @param model
     * @return
     */
    @PostMapping("/user/changepassword")
    public String changepassword(String password, String newpassword_1, String newpassword_2, Model model){

        //对比第一二次输入的新密码
        if (!newpassword_1.equals(newpassword_2)){
            String errorMsg = "两次输入的密码不一致";
            model.addAttribute("errorMsg",errorMsg);
            return "changepassword";
        }
        //修改成功
        boolean b = sysUserService.changePassword(password, newpassword_1);
        if (b){
            return "login";
        }
        //修改失败返回修改密码页面
        String errorMsg = "新密码不能与旧密码相同";
        model.addAttribute("errorMsg",errorMsg);
        return "changepassword";
    }

    /**
     * 用户资料页面
     * @return
     */
    @RequestMapping("/user/userData_page")
    public String userData_page(){
        return "userData";
    }

    /**
     * 保存用户资料
     * @return
     */
    @PostMapping("/user/updateUserData")
    public String updateUserData(SysUserdataEntity sysUserdataEntity){

        //获取当前用户
        SysUserEntity sysUserEntity = (SysUserEntity) getSubject().getPrincipal();
        //给资料赋上用户名
        sysUserdataEntity.setUsername(sysUserEntity.getUsername());

        sysUserdataService.updateUserdata(sysUserdataEntity);
        return "userData";
    }

}
