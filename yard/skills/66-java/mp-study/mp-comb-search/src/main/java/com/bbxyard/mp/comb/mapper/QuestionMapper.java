package com.bbxyard.mp.comb.mapper;

import com.bbxyard.mp.comb.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbxyard.mp.comb.vo.QuestionStudentVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 问答表 Mapper 接口
 * </p>
 *
 * @author bbxyard
 * @since 2019-09-27
 */
public interface QuestionMapper extends BaseMapper<Question> {
    /**
     *
     * @param page 翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @return
     */
    @Select("SELECT mp_comb_question.*,mp_comb_student.`name` FROM mp_comb_question,mp_comb_student WHERE mp_comb_question.student_id=mp_comb_student.id")
    List<QuestionStudentVO> getQuestionStudent(Page<QuestionStudentVO> page);

}
