package com.miao.service.impl;

import com.miao.entity.SysUserEntity;
import com.miao.mapper.SysUserMapper;
import com.miao.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

import static org.apache.shiro.SecurityUtils.getSubject;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public boolean addUser(SysUserEntity sysUserEntity) {
        //判断该用户是否存在
        if (findByName(sysUserEntity.getUsername()) == null) {
            //获取当前时间
            Timestamp createTime = new Timestamp(new Date().getTime());
            sysUserEntity.setCreate_date(createTime);
            sysUserEntity.setUpdate_date(createTime);

            sysUserMapper.insertUser(sysUserEntity);
            return true;
        }
        return false;
    }

    @Override
    public SysUserEntity findByName(String username) {
        return sysUserMapper.selectByName(username);
    }

    @Override
    public void openVip() {
        SysUserEntity sysUserEntity = (SysUserEntity) getSubject().getPrincipal();
        //设置权限
        String perms = "sysUser:vip";
        sysUserEntity.setPerms(perms);
        //修改更新时间
        Timestamp createTime = new Timestamp(new Date().getTime());
        sysUserEntity.setUpdate_date(createTime);
        sysUserMapper.updateUser(sysUserEntity);
    }
}
