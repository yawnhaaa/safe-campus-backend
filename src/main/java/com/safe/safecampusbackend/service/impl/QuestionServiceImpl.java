package com.safe.safecampusbackend.service.impl;

import com.safe.safecampusbackend.dao.QuestionContentDAO;
import com.safe.safecampusbackend.dao.QuestionDAO;
import com.safe.safecampusbackend.model.entity.QuestionContentEntity;
import com.safe.safecampusbackend.model.entity.QuestionEntity;
import com.safe.safecampusbackend.model.vo.QuestionListVO;
import com.safe.safecampusbackend.service.QuestionService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionDAO questionDAO;
    private final QuestionContentDAO questionContentDAO;

    public Result<List<QuestionListVO>> getQuestionList() {
        List<QuestionEntity> questionEntityList = questionDAO.selectList(null);
        List<QuestionContentEntity> questionContentEntityList = questionContentDAO.selectList(null);
        // 筛选掉 is_delete 为 1 的数据
        List<QuestionEntity> filteredQuestionList = questionEntityList.stream()
                .filter(question -> question.getIsDelete() != 1)
                .toList();

        // 筛选掉 is_delete 为 1 的数据
        List<QuestionContentEntity> filteredQuestionContentList = questionContentEntityList.stream()
                .filter(content -> content.getIsDelete() != 1)
                .collect(Collectors.toList());

        // 过滤出至少有两条子表数据的问题
        List<QuestionEntity> filteredQuestionListWithEnoughContent = filteredQuestionList.stream()
                .filter(question -> countContent(question.getId(), filteredQuestionContentList) >= 2)
                .toList();

        // 将符合条件的问题转换为 QuestionListVO
        List<QuestionListVO> result = filteredQuestionListWithEnoughContent.stream()
                .map(question -> {
                    // 创建 QuestionListVO 对象
                    QuestionListVO vo = new QuestionListVO();
                    // 设置属性
                    vo.setType(question.getType());
                    vo.setTitle(question.getTitle());
                    vo.setAnalysis(question.getAnalysis());
                    vo.setIsSubmit(question.getIsSubmit());
                    vo.setIsDelete(question.getIsDelete());
                    // 获取该问题对应的子表数据
                    List<QuestionContentEntity> contentList = filteredQuestionContentList.stream()
                            .filter(content -> content.getQuestionId().equals(question.getId()))
                            .collect(Collectors.toList());
                    // 设置问题的子表数据列表
                    vo.setQuestionContentEntityList(contentList);
                    return vo;
                })
                .collect(Collectors.toList());

        return ResultUtil.success(result);
    }

    // 辅助方法，用于计算问题的子表数据数量
    private long countContent(Long questionId, List<QuestionContentEntity> contentList) {
        return contentList.stream()
                .filter(content -> content.getQuestionId().equals(questionId))
                .count();
    }
}
