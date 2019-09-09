package com.bbxyard.mp.xenum.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

public enum AgeEnum implements IEnum<Integer> {
    ONE(1, "一岁"),
    TWO(2, "两岁"),
    THREE(3, "三岁");

    private int value;
    private String desc;

    AgeEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
