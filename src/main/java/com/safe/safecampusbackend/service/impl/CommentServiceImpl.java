package com.safe.safecampusbackend.service.impl;

import com.safe.safecampusbackend.dao.CommentDAO;
import com.safe.safecampusbackend.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDAO commentDAO;
}
