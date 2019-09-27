package com.bbxyard.mp.comb.vo;

import com.bbxyard.mp.comb.entity.Question;
import lombok.Data;

@Data
public class QuestionStudentVO extends Question {

    /**
     * 学生名称
     */
    private String name;

}
