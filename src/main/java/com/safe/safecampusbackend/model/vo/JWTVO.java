package com.safe.safecampusbackend.model.vo;

import lombok.Data;

@Data
public class JWTVO {
    private String jwt;
    private Long userId;
    private String userName;
}
