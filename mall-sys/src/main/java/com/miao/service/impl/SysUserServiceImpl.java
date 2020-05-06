package com.miao.service.impl;

import com.miao.entity.SysUserEntity;
import com.miao.mapper.SysUserMapper;
import com.miao.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public boolean addUser(SysUserEntity sysUserEntity) {
        Timestamp createTime = new Timestamp(new Date().getTime());
        sysUserEntity.setCreateDate(createTime);
        sysUserEntity.setUpdateDate(createTime);
        sysUserMapper.insertUser(sysUserEntity);
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        return true;
    }
}
