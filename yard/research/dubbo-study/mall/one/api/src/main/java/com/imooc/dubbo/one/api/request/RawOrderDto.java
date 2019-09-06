package com.imooc.dubbo.one.api.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RawOrderDto implements Serializable {

    // 商品id
    private Integer itemId;

    // 下单数量
    private Integer total;

    // 客户姓名
    private String customerName;

}
