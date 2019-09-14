package com.bbxyard.mp.typehandler.entity;

import lombok.Data;

import java.nio.DoubleBuffer;


@Data
public class Currency {

    /**
     * 类型: RMB USD
     */
    private String type;

    /**
     * 金额
     */
    private Double amount;
}
