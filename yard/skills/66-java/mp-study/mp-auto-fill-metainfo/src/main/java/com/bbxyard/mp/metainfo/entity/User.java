package com.bbxyard.mp.metainfo.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mp_afm_user")
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
