package com.safe.safecampusbackend.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private Long id;
    private Integer type;
    private String title;
    private String analysis;
    private List<QuestionContentDTO> questionContentDTOList;
}
