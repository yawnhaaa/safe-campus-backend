package com.safe.safecampusbackend.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class InfoVO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private String author_id;
    private String infoDate;
    private Integer infoLike;
    private Integer infoCollect;
}
