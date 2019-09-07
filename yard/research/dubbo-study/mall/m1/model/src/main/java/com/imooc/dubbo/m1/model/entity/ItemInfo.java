package com.imooc.dubbo.m1.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemInfo extends XItem implements Serializable {

    @TableId
    private long id;

    private String code;

    private String name;

    private BigDecimal price;

    private Date createTime;

}
