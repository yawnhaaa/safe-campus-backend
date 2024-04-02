package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class UserDetailDTO {
    private Long id;
    private String name;
    private String intro;
    private Integer gender;
    private String school;
    private String college;
    private String stuClass;
}
