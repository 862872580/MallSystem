package com.miao.service.impl;

import com.miao.entity.SysUserEntity;
import com.miao.entity.SysUserdataEntity;
import com.miao.mapper.SysUserdataMapper;
import com.miao.service.SysUserdataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.shiro.SecurityUtils.getSubject;

@Component
public class SysUserdataServiceImpl implements SysUserdataService {

    @Autowired
    private SysUserdataMapper sysUserdataMapper;

    /**
     * 添加用户资料
     * @param sysUserdataEntity
     * @return
     */
    @Override
    public boolean addUserdata(SysUserdataEntity sysUserdataEntity) {
        sysUserdataMapper.insertUserdata(sysUserdataEntity);
        return true;
    }

    /**
     * 更新用户资料
     * @param sysUserdataEntity
     * @return
     */
    @Override
    public boolean updateUserdata(SysUserdataEntity sysUserdataEntity) {
        sysUserdataMapper.updateUserdata(sysUserdataEntity);
        return true;
    }
}
