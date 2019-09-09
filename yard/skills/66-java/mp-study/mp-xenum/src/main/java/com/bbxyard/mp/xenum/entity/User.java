package com.bbxyard.mp.xenum.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.bbxyard.mp.xenum.enums.AgeEnum;
import com.bbxyard.mp.xenum.enums.GenderEnum;
import com.bbxyard.mp.xenum.enums.GradeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("mp_enum_user")
public class User extends BaseEntity {

    private String name;

    private String email;

    /**
     * IEnum接口的枚举处理
     */
    private AgeEnum age;

    /**
     * 原生枚举： 默认使用枚举值顺序： 0: UNK, 1: MALE， 2: FEMALE
     */
    private GenderEnum gender;

    /**
     * 原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库的值对应该注解对应的属性
     */
    private GradeEnum grade;

}
