package com.miao.service.impl;

import com.miao.entity.SysUserEntity;
import com.miao.mapper.SysUserMapper;
import com.miao.service.SysUserService;
import com.miao.util.Md5Util;
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

            //把密码进行加密
            String Md5password = Md5Util.getMD5(sysUserEntity.getPassword(), sysUserEntity.getUsername());
            sysUserEntity.setPassword(Md5password);

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

    @Override
    public boolean changePassword(String password, String newpassword) {

        //获取当前用户数据
        SysUserEntity sysUserEntity = (SysUserEntity) getSubject().getPrincipal();
        String username = sysUserEntity.getUsername();

        //新密码不能与旧密码相同
        if (!password.equals(newpassword)) {
            //判断密码是否正确
            String md5password = Md5Util.getMD5(password, username);
            System.out.println(sysUserEntity.getPassword());
            System.out.println(md5password);
            if (!sysUserEntity.getPassword().equals(md5password)) {
                //密码不正确
                System.out.println("密码不正确");
                return false;
            }
            //密码正确: 设置新密码
            String newMd5password = Md5Util.getMD5(newpassword, username);
            sysUserEntity.setPassword(newMd5password);
            sysUserMapper.updateUserPassword(sysUserEntity);
            System.out.println("修改密码完毕");
            return true;
        }
        System.out.println("新密码不能与旧密码相同");
        return false;
    }

}
