package com.safe.safecampusbackend.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MaterialListVO {
    private Long id;
    private String imgSrc;
    private String title;
    private String author;
    private Date materialDate;
}
