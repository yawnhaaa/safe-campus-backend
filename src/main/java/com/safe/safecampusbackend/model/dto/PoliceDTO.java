package com.safe.safecampusbackend.model.dto;

import lombok.Data;

@Data
public class PoliceDTO {
    private Long id;
    private String longitude;
    private String latitude;
    private String name;
    private String address;
    private String intro;
    private String tel;
}
