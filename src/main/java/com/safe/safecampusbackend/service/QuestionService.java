package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.vo.QuestionListVO;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface QuestionService {
    /**
     * 获取题目数据
     *
     * @return 题目数组
     */
    Result<List<QuestionListVO>> getQuestionList();
}
