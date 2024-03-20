package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.vo.InfoListVO;
import com.safe.safecampusbackend.model.vo.InfoVO;
import com.safe.safecampusbackend.service.InfoService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class InfoController {
    private final InfoService infoService;

    @GetMapping("/getInfoList")
    public Result<List<InfoListVO>> getInfoList() {
        return infoService.getInfoList();
    }

    @GetMapping("/getInfoById/{id}")
    public Result<InfoVO> getInfoById(@PathVariable String id) {
        return infoService.getInfoById(id);
    }
}
