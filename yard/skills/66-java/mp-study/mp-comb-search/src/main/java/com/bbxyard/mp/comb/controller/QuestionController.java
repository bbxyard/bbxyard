package com.bbxyard.mp.comb.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bbxyard.mp.comb.entity.Question;
import com.bbxyard.mp.comb.service.QuestionService;
import com.bbxyard.mp.comb.service.StudentService;
import com.bbxyard.mp.comb.vo.QuestionStudentVO;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 问答表 前端控制器
 * </p>
 *
 * @author bbxyard
 * @since 2019-09-27
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private StudentService studentService;

    @GetMapping("/list/{page}/{size}")
    public Map<String, Object> getAllByPage(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> map = new HashMap<>();
        IPage<Question> questionPage = questionService.page(new Page<Question>(page, size));
        if (questionPage.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", questionPage);
        }
        return map;
    }

    @GetMapping("/xlist/{page}/{size}")
    public Map<String, Object> getCombedAllByPage(@PathVariable Integer page, @PathVariable Integer size) {
        Map<String, Object> map = new HashMap<>();
        Page<QuestionStudentVO> qsPage = questionService.getQuestionStudent(new Page<>(page, size));
        if (qsPage.getRecords().size() == 0) {
            map.put("code", 400);
        } else {
            map.put("code", 200);
            map.put("data", qsPage);
        }
        return map;
    }
}

