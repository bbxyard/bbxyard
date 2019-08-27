package com.bbxyard.spboot.enums;

public enum Gender {
    UNK(0, "unkonwn"),
    MALE(1, "male"),
    FEMALE(2, "female");

    private int value; // 值
    private String title; // 标题

    Gender(int value, String title) {
        this.value = value;
        this.title = title;
    }

    public static Gender valueOf(int value) {
        for (Gender g: values()) {
            if (g.value == value) {
                return g;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }
}
