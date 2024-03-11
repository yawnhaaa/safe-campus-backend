package com.safe.safecampusbackend.model.vo;

import lombok.Data;

@Data
public class DetailVO {
    private Long id;
    private String email;
    private String intro;
    private Integer gender;
    private String school;
    private String college;
    private String stuNum;
}
