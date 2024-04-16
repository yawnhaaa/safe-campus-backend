package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.CommentDTO;
import com.safe.safecampusbackend.model.vo.CommentVO;
import com.safe.safecampusbackend.service.CommentService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/getComment/{infoId}")
    public Result<List<CommentVO>> getComment(@PathVariable Long infoId) {
        return commentService.getComment(infoId);
    }

    @PostMapping("/protected/sendComment")
    public Result<String> sendComment(@RequestBody CommentDTO commentDTO) {
        return commentService.sendComment(commentDTO);
    }
}
