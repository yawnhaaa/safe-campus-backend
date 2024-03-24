package com.safe.safecampusbackend.service.impl;

import com.safe.safecampusbackend.dao.QuestionContentDAO;
import com.safe.safecampusbackend.dao.QuestionDAO;
import com.safe.safecampusbackend.model.vo.QuestionListVO;
import com.safe.safecampusbackend.service.QuestionService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDAO questionDAO;
    private final QuestionContentDAO questionContentDAO;

    public Result<QuestionListVO> getQuestionList(){
    }
}
