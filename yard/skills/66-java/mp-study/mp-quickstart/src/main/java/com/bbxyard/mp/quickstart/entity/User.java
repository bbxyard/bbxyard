package com.bbxyard.mp.quickstart.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("mp_qs_user")
public class User {

    @TableId
    private Long id;

    private String name;

    private Integer age;

    private String email;

}
