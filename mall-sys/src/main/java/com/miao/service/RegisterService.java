package com.miao.service;

import com.miao.entity.SysUserEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

public interface RegisterService {
    String register(SysUserEntity sysUserEntity, BindingResult br, Model model, String repassword);
}
