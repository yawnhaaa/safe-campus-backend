package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;
}
