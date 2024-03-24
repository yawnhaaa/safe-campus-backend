package com.safe.safecampusbackend.model.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class IssueInfoDTO {
    private String title;
    private String author;
    private Long authorId;
    private String content;
    private MultipartFile file;
}
