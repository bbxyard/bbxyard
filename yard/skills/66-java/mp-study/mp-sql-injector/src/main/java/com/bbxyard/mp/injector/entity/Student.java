package com.bbxyard.mp.injector.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "mp_si_student")
public class Student {

    private Long id;

    private String name;

    private Integer age;

}
