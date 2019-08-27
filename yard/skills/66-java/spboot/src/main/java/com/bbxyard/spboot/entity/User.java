package com.bbxyard.spboot.entity;

import com.bbxyard.spboot.enums.Gender;

public class User {

    private String name; // 名字
    private int age; // 年龄
    private Gender gender; // 性别

    public User(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public String toString() {
        String s = String.format("\"name\":\"%s\", \"age\":%d, \"gender\":\"%s\"}", name, age, gender.getTitle());
        return s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}

