package com.miao.service.impl;

import com.miao.entity.SysUserEntity;
import com.miao.entity.SysUserdataEntity;
import com.miao.service.RegisterService;
import com.miao.service.SysUserService;
import com.miao.service.SysUserdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

@Component
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserdataService sysUserdataService;


    @Override
    public String register(SysUserEntity sysUserEntity, BindingResult br, Model model, String repassword) {
        model.addAttribute("sysUserEntity", sysUserEntity);
        int errorCount = br.getErrorCount();

        //判断两次输入密码是否一致
        if ( !repassword.equals(sysUserEntity.getPassword())){
            String repasswordErrorMSG = "两次密码不一致,请重新输入";
            model.addAttribute("repasswordErrorMSG", repasswordErrorMSG);
            errorCount++;
        }
        //birthday不输入会报错
        if (br.getFieldError("birthday") != null){
            errorCount--;
        }


        if (errorCount > 0){

            FieldError usernameError = br.getFieldError("username");
            FieldError passwordError = br.getFieldError("password");
            FieldError phonenumberError = br.getFieldError("phonenumber");
            FieldError emailError = br.getFieldError("email");
            List<ObjectError> allErrors = br.getAllErrors();

            if(usernameError != null){
                String usernameErrorMSG = usernameError.getDefaultMessage();
                model.addAttribute("usernameErrorMSG", usernameErrorMSG);
            }
            if(passwordError != null){
                String passwordErrorMSG = passwordError.getDefaultMessage();
                model.addAttribute("passwordErrorMSG", passwordErrorMSG);
            }

            if(phonenumberError != null){
                String phonenumberErrorMSG = phonenumberError.getDefaultMessage();
                model.addAttribute("phonenumberErrorMSG", phonenumberErrorMSG);
            }
            if(emailError != null){
                String emailErrorMSG = emailError.getDefaultMessage();
                model.addAttribute("emailErrorMSG", emailErrorMSG);
                System.out.println(emailErrorMSG);
            }
            System.out.println("errorCount = " + errorCount);
            System.out.println(allErrors);
            return "register";
        }

        //添加用户
        boolean b = sysUserService.addUser(sysUserEntity);

        if (b) {

            //创建用户资料
            SysUserdataEntity sysUserdataEntity = new SysUserdataEntity();

            //用户名相同
            sysUserdataEntity.setUsername(sysUserEntity.getUsername());
            sysUserdataService.addUserdata(sysUserdataEntity);

            return "login";
        }

        model.addAttribute("msg", "用户名已存在");
        return "register";
    }
}
