package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.vo.VisualVO;
import com.safe.safecampusbackend.service.VisualService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class VisualController {
    private final VisualService visualService;

    @GetMapping("/getVisualList")
    public Result<List<VisualVO>> getVisualList() {
        return visualService.getVisualList();
    }
}
