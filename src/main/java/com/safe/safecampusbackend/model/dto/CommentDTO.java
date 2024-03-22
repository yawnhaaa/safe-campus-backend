package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long infoId;
    private String comment;
    private Long commentId;
    private String commentName;
    private Long commentedId;
    private String commentedName;
    private Long topId;
}
