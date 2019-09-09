package com.bbxyard.mp.xenum.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum GradeEnum {
    G1(1, "一年级"),
    G2(2, "二年级"),
    G3(3, "三年级"),
    G4(4, "四年级"),
    G5(5, "五年级"),
    G6(6, "六年级"),
    G7(7, "七年级"),
    G8(8, "八年级"),
    G9(9, "九年级");

    GradeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumValue
    private final int code;
    private final String desc;

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}

