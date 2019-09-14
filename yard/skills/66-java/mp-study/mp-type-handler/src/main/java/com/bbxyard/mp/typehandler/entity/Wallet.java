package com.bbxyard.mp.typehandler.entity;

import lombok.Data;

import java.util.List;


@Data
public class Wallet {

    /**
     * 名称
     */
    private String name;

    /**
     * 各种货币
     */
    private List<Currency> currencyList;

}
