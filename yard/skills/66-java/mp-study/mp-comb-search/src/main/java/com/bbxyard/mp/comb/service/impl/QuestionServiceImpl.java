package com.bbxyard.mp.comb.service.impl;

import com.bbxyard.mp.comb.entity.Question;
import com.bbxyard.mp.comb.mapper.QuestionMapper;
import com.bbxyard.mp.comb.service.QuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 问答表 服务实现类
 * </p>
 *
 * @author bbxyard
 * @since 2019-09-27
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

}
