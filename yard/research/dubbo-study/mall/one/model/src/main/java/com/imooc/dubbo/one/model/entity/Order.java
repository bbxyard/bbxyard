package com.imooc.dubbo.one.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order implements Serializable {

    private Integer id;

    private Integer itemId;

    private Integer total;

    private String customerName;

    private Date orderTime;

    private Integer isActive;

    private Date updateTime;

}
