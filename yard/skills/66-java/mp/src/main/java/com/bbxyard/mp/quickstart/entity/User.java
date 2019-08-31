package com.bbxyard.mp.quickstart.entity;


import lombok.Data;

@Data
public class User {
    private Long id;        // id
    private String name;    // 姓名
    private Integer age;    // 年龄
    private String email;   // 邮箱
}
