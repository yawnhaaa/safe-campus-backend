package com.safe.safecampusbackend.model.vo;

import lombok.Data;

@Data
public class UserLikeVO {
    private Long id;
    private String title;
    private String author;
    private Integer like;
}
