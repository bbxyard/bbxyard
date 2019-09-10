package com.bbxyard.mp.metainfo.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

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

    @TableField(fill = FieldFill.INSERT, value = "create_time")
    private LocalDateTime createTime;

    // @TableField(fill = FieldFill.INSERT_UPDATE, value = "update_time", update = "%s+1")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private String remark;
}
