package com.bbxyard.mp.logic.entity;


import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mp_ld_user")
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    private Integer deleted;
}
