package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class InfoUserDTO {
    private Long userId;
    private Long infoId;
    private int type;
}
