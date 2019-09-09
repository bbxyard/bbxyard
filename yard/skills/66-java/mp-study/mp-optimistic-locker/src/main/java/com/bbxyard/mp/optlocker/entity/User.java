package com.bbxyard.mp.optlocker.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mp_ol_user")
public class User {

    @TableId
    private Long id;

    @TableField("name")
    private String name;

    private Integer age;

    private String email;

    @TableLogic
    private Integer deleted;

    @Version
    private Integer version;

}
