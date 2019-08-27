package com.bbxyard.spboot.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class WebUser {

    private String name; // 用户名

    @JsonIgnore
    private String password; // 密码

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss a", locale="zh", timezone = "GMT+8")
    private Date lastLogin;  // 最后登陆时间

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String memo; // 备注

    private int age; // 年龄

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
