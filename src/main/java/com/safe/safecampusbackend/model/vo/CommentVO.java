package com.safe.safecampusbackend.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVO {
    private Long id;
    private String comment;
    private Long commentId;
    private String commentName;
    private Long commentedId;
    private String commentedName;
    private Long topId;
    private Date commentTime;
    private Integer isDelete;
}
