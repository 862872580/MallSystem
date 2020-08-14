package com.miao.service;

import com.miao.entity.SysUserEntity;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface LoginService {
    String login(SysUserEntity sysUserEntity, Model model, HttpSession session);
}
