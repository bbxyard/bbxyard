package com.bbxyard.mp.opt_lock.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("mp_ol_user")
public class User {

    @TableId
    private Long id;

    @TableField("name")
    private String name;        // 值 映射

    private Integer age;

    private String email;

    @TableLogic
    private Integer deleted;    // 逻辑删除

    @Version
    private Integer version;    // 乐观锁 -> 版本

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String operator;    // 操作员
}
