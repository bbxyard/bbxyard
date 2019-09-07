package com.imooc.dubbo.m1.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
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
public class Order extends XItem implements Serializable {

    @TableId
    private long id;

    /**
     * 商品id
     */
    private long itemId;

    private Integer total;

    private String customerName;

    /**
     * 下单时间
     */
    private Date orderTime;
}
