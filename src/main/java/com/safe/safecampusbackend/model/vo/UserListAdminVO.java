package com.safe.safecampusbackend.model.vo;

import lombok.Data;

@Data
public class UserListAdminVO {
    private Long id;
    private String name;
    private String email;
    private String school;
    private String college;
    private String stu_num;
    private Integer is_delete;
}
