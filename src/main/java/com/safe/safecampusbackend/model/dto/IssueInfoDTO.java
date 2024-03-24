package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class IssueInfoDTO {
    private String title;
    private String author;
    private Long authorId;
    private String content;
}
