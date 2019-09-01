package com.bbxyard.mp.annotation.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("mp_annotation_user")
public class User {

    @TableId
    private Integer userId;

    @TableField("user_name")
    private String name;        // 值 映射

    private Integer age;

    private String email;

    @TableLogic
    private Integer deleted;    // 逻辑删除

    @Version
    private Integer version;    // 乐观锁 -> 版本
}
