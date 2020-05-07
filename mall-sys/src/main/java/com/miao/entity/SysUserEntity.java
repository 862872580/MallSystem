package com.miao.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.sql.Timestamp;
import java.util.Objects;

public class SysUserEntity {

    private int id;

    @NotNull(message = "姓名不能为空")
    @Size(min = 3, max = 18, message = "姓名长度应在{min}-{max}个字符")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 9, max = 18, message = "密码长度应在{min}-{max}个字符")
    private String password;


    @Range(min = 1, max = 150, message = "年龄必须为{min}-{max}岁")
    private Integer age;

    @Email
    private String email;

    private String realname;
    private Timestamp create_date;
    private Timestamp update_date;
    private int del_flag;    //用户是否注销
    private String perms; //用户权限

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Timestamp getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Timestamp create_date) {
        this.create_date = create_date;
    }

    public Timestamp getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Timestamp update_date) {
        this.update_date = update_date;
    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }


    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysUserEntity that = (SysUserEntity) o;
        return id == that.id &&
                del_flag == that.del_flag &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(age, that.age) &&
                Objects.equals(email, that.email) &&
                Objects.equals(realname, that.realname) &&
                Objects.equals(create_date, that.create_date) &&
                Objects.equals(update_date, that.update_date) &&
                Objects.equals(perms, that.perms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, age, email, realname, create_date, update_date, del_flag, perms);
    }

    @Override
    public String toString() {
        return "SysUserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", realname='" + realname + '\'' +
                ", create_date=" + create_date +
                ", update_date=" + update_date +
                ", del_flag=" + del_flag +
                ", perms='" + perms + '\'' +
                '}';
    }
}
