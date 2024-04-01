package com.safe.safecampusbackend.model.vo;

import lombok.Data;

@Data
public class UserDetailVO {
    private Long id;
    private String stuId;
    private String name;
    private String email;
    private String intro;
    private Integer gender;
    private String school;
    private String college;
    private String stuClass;
}
