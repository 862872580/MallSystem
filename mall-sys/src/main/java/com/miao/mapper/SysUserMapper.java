package com.miao.mapper;

import com.miao.entity.SysUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface SysUserMapper {
    @Insert("insert into sys_user values (#{id}, #{username}" +
            ", #{password}, #{age}, #{email}" +
            ", #{realname}, #{createDate}, #{updateDate}" +
            ", #{delFlag})")
    void insertUser(SysUserEntity sysUserEntity);

    @Select("select * from sys_user where username=#{username}")
    SysUserEntity selectByName(String username);
}
