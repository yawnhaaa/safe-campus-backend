package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String email;
    private String passwd;
}
