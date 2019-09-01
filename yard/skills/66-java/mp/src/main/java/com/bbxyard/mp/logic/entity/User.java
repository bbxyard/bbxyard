package com.bbxyard.mp.logic.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mp_ld_user")
public class User {

    private Integer id;
    private String name;
    private Integer age;
    private String email;

    @TableLogic
    private Integer deleted;
}
