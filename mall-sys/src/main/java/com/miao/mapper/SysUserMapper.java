package com.miao.mapper;

import com.miao.entity.SysUserEntity;
import org.apache.ibatis.annotations.Insert;

public interface SysUserMapper {
    @Insert("insert into sys_user values (#{id}, #{username}" +
            ", #{password}, #{age}, #{email}" +
            ", #{realname}, #{createDate}, #{updateDate}" +
            ", #{delFlag})")
    void insertUser(SysUserEntity sysUserEntity);
}
