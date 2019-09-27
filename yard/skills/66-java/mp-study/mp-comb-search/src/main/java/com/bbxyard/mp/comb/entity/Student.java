package com.bbxyard.mp.comb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 学生表
 * </p>
 *
 * @author bbxyard
 * @since 2019-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_comb_student")
public class Student implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 学生主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生名称
     */
    private String name;

    /**
     * 学生密码
     */
    private String password;

    /**
     * 学生积分数
     */
    private Integer point;

    /**
     * 学生邮件地址
     */
    private String email;

    /**
     * 学生手机号码
     */
    private String phone;

    /**
     * 学生学号
     */
    private String sn;

    /**
     * 真实姓名 
     */
    private String realName;


}
