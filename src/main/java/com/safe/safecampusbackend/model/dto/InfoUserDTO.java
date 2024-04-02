package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class InfoUserDTO {
    private Long id;
    private Long infoId;
    private int type;
    private Boolean isStatus;
}
