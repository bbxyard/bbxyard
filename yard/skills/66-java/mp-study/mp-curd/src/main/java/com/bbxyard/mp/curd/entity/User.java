package com.bbxyard.mp.curd.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("mp_curd_user")
public class User {

    private Long id;
    private String name;
    private Integer age;
    private String email;

    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private Integer count;
}
