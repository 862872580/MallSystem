package com.miao.mapper;

import com.miao.entity.SysUserdataEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

public interface SysUserdataMapper {
    @Insert("insert into sys_userdata values " +
            "(#{id}, #{username}, nickname = #{nickname}, #{sex}, #{label}, #{occupation}, #{school})")
    void insertUserdata(SysUserdataEntity sysUserdataEntity);

    @Update("update sys_userdata set " +
            "nickname = #{nickname}, sex = #{sex}, label = #{label}, occupation = #{occupation}, school = #{school} " +
            "where username = #{username}")
    boolean updateUserdata(SysUserdataEntity sysUserdataEntity);

}
