package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String name;
    private String email;
    private String passwd;
    private String code;
}
