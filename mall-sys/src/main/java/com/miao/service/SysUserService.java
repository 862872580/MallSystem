package com.miao.service;

import com.miao.entity.SysUserEntity;

public interface SysUserService {
    boolean addUser(SysUserEntity sysUserEntity);

    SysUserEntity findByName(String username);

    void openVip();

    boolean changePassword(String password, String newpassword);
}
