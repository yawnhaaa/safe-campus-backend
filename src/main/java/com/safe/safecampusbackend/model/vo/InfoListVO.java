package com.safe.safecampusbackend.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class InfoListVO {
    private Long id;
    private String title;
    private String content;
    private String author;
    private Date infoDate;
    private Integer infoLike;
    private String img;
}
