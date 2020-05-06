package com.miao.service;

import com.miao.entity.SysUserEntity;

public interface SysUserService {
    boolean addUser(SysUserEntity sysUserEntity);

    boolean login(String username, String password);

    SysUserEntity findByName(String username);
}
