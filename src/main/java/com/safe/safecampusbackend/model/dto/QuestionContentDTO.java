package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class QuestionContentDTO {
    private Long id;
    private Long questionId;
    private String content;
    private Boolean isTrue;
}
