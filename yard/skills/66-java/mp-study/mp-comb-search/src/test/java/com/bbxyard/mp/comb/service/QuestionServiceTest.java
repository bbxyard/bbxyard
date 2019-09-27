package com.bbxyard.mp.comb.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbxyard.mp.comb.entity.Question;
import com.bbxyard.mp.comb.vo.QuestionStudentVO;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class QuestionServiceTest {

    @Resource
    private QuestionService questionService;

    @Test
    public void testInsert() {
        int cnt = 100;
        List<Question> questions = new ArrayList<>(cnt);
        for (int i = 0; i < cnt; ++i) {
            Question question = new Question();
            question.setContent(String.format("这是内容-%d", i));
            question.setScore(RandomUtils.nextInt(1, 100));
            question.setStudentId(RandomUtils.nextInt(11, 20));
            questions.add(question);
        }
        questionService.saveBatch(questions);
    }

    @Test
    public void testPageSearch() {
        Page<QuestionStudentVO> page = new Page<>(2, 6);
        Page<QuestionStudentVO> res = questionService.getQuestionStudent(page);
        System.out.println(res);
    }
}