package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class UserUpdatePasswdDTO {
    private Long id;
    private String passwd;
}
