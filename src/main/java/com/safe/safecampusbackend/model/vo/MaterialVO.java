package com.safe.safecampusbackend.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class MaterialVO {
    private String src;
    private String title;
    private String author;
    private Date materialDate;
    private Integer download;
}
