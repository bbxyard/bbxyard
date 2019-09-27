package com.bbxyard.mp.comb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbxyard.mp.comb.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bbxyard.mp.comb.vo.QuestionStudentVO;

/**
 * <p>
 * 问答表 服务类
 * </p>
 *
 * @author bbxyard
 * @since 2019-09-27
 */
public interface QuestionService extends IService<Question> {

    Page<QuestionStudentVO> getQuestionStudent(Page<QuestionStudentVO> page);

}
