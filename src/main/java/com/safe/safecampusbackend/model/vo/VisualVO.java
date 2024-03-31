package com.safe.safecampusbackend.model.vo;

import lombok.Data;

@Data
public class VisualVO {
    private String title;
    private Integer number;

    public VisualVO() {
    }

    public VisualVO(String title, int imageCount) {
    }
}
