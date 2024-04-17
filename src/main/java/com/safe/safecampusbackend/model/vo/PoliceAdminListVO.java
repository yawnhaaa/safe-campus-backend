package com.safe.safecampusbackend.model.vo;

import lombok.Data;

@Data
public class PoliceAdminListVO {
    private Long id;
    private Double longitude;
    private Double latitude;
    private String name;
    private String address;
    private String intro;
    private String tel;
    private Integer isDelete;
}
