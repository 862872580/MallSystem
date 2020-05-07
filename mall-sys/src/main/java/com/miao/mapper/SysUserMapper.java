package com.miao.mapper;

import com.miao.entity.SysUserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SysUserMapper {
    @Insert("insert into sys_user values (#{id}, #{username}" +
            ", #{password}, #{age}, #{email}" +
            ", #{realname}, #{create_date}, #{update_date}" +
            ", #{del_flag}, #{perms})")
    void insertUser(SysUserEntity sysUserEntity);

    @Select("select * from sys_user where username=#{username}")
    SysUserEntity selectByName(String username);

    @Update("update sys_user set update_date = #{update_date} , perms = #{perms} where username = #{username}")
    boolean updateUser(SysUserEntity sysUserEntity);

    @Update("update sys_user set update_date = #{update_date} , password = #{password} where username = #{username}")
    void updateUserPassword(SysUserEntity sysUserEntity);
}
