package com.bbxyard.mp.comb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 问答表
 * </p>
 *
 * @author bbxyard
 * @since 2019-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mp_comb_question")
public class Question implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 问答主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 学生外键id
     */
    private Integer studentId;

    /**
     * 问题内容
     */
    private String content;

    /**
     * 问题发布时间
     */
    private LocalDate date;

    /**
     * 问题悬赏积分
     */
    private Integer score;


}
