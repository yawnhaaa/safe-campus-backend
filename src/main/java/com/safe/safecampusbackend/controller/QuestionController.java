package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.vo.QuestionListVO;
import com.safe.safecampusbackend.service.QuestionService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/getQuestionList")
    public Result<List<QuestionListVO>> getQuestionList() {
        return questionService.getQuestionList();
    }
}
