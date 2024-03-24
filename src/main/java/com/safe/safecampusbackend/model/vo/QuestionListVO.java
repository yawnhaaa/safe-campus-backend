package com.safe.safecampusbackend.model.vo;

import com.safe.safecampusbackend.model.entity.QuestionContentEntity;
import lombok.Data;

import java.util.List;

@Data
public class QuestionListVO {
    private Integer type;
    private String title;
    private String analysis;
    private Boolean isSubmit;
    private Integer isDelete;
    private List<QuestionContentEntity> questionContentEntityList;
}
