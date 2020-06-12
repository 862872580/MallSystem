package com.miao.entity;

import javax.validation.constraints.*;
import java.sql.Date;

public class SysUserEntity {

    private int id;

    @NotNull(message = "姓名不能为空")
    @Size(min = 3, max = 18, message = "姓名长度应在{min}-{max}个字符")
    private String username;

    @NotNull(message = "密码不能为空")
    @Size(min = 6, max = 18, message = "密码长度应在{min}-{max}个字符")
    private String password;

    @NotNull(message = "手机号码不能为空")
    @Pattern(regexp = "^1[34578]\\d{9}$", message = "手机号格式不正确")
    private String phonenumber;

    private String photo;

    private Date birthday;

    @Email(message = "邮箱格式不正确")
    private String email;

    private String realname;
    private Date create_date;
    private Date update_date;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
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
    public String toString() {
        return "SysUserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phonenumber=" + phonenumber +
                ", photo='" + photo + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", realname='" + realname + '\'' +
                ", create_date=" + create_date +
                ", update_date=" + update_date +
                ", del_flag=" + del_flag +
                ", perms='" + perms + '\'' +
                '}';
    }
}
