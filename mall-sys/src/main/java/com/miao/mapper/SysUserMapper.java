package com.miao.mapper;

import com.miao.entity.SysUserEntity;
import org.apache.ibatis.annotations.Insert;

public interface SysUserMapper {
    @Insert("insert into sys_user values")
    public int createUser(SysUserEntity sysUserEntity);
}
