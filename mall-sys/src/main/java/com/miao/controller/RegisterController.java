package com.miao.controller;

import com.miao.entity.SysUserEntity;
import com.miao.entity.SysUserdataEntity;
import com.miao.service.RegisterService;
import com.miao.service.SysUserService;
import com.miao.service.SysUserdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegisterController {


    @Autowired
    private RegisterService registerService;
    /**
     *
     * @return 跳转到注册页面
     */
    @RequestMapping("/user/register_page")
    public String register_page(){
        return "register";
    }

    /**
     * 注册
     * @param sysUserEntity
     * @return 成功跳转到登录页面
     * 失败跳转到错误页面
     */
    @PostMapping("/user/register")
    public String register(@Valid SysUserEntity sysUserEntity, BindingResult br, Model model,String repassword){

        return registerService.register(sysUserEntity, br, model, repassword);

    }

}
