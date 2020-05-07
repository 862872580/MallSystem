package com.miao.controller;

import com.miao.entity.SysUserEntity;
import com.miao.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {
    @Autowired
    private SysUserService sysUserService;
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
    public String register(@Valid SysUserEntity sysUserEntity, BindingResult br, Model model){

        model.addAttribute("sysUserEntity", sysUserEntity);
        int errorCount = br.getErrorCount();
        if(errorCount > 0){
            FieldError usernameError = br.getFieldError("username");
            FieldError passwordError = br.getFieldError("password");
            FieldError ageError = br.getFieldError("age");
            FieldError emailError = br.getFieldError("email");

            if(usernameError != null){
                String usernameErrorMSG = usernameError.getDefaultMessage();
                model.addAttribute("usernameErrorMSG", usernameErrorMSG);
            }
            if(passwordError != null){
                String passwordErrorMSG = passwordError.getDefaultMessage();
                model.addAttribute("passwordErrorMSG", passwordErrorMSG);
            }
            if(ageError != null){
                String ageErrorMSG = ageError.getDefaultMessage();
                model.addAttribute("ageErrorMSG", ageErrorMSG);
            }
            if(emailError != null){
                String emailErrorMSG = emailError.getDefaultMessage();
                model.addAttribute("emailErrorMSG", emailErrorMSG);
            }
            return "register";
        }

        boolean b = sysUserService.addUser(sysUserEntity);
        if (b) {
            return "login";
        }
        model.addAttribute("msg", "用户名已存在");
        return "register";

    }
}
