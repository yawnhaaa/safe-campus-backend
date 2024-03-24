package com.safe.safecampusbackend.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class IssueMaterialDTO {
    private String title;
    private String author;
    private Long authorId;
    private Integer materialType;
    private MultipartFile file;
    private MultipartFile imgFile;
}
