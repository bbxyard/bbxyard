package com.bbxyard.mp.xenum.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;


public enum GenderEnum {
    UNK,
    MALE,
    FEMALE
}

enum GenderEnum2 {

    UNK(0, "未知"),
    MALE(1, "先生"),
    FEMALE(2, "女士");

    GenderEnum2(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    @EnumValue
    private int code;
    private String desc;

}

