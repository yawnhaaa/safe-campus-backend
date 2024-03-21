package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.InfoUserDTO;
import com.safe.safecampusbackend.model.dto.InfoUserStatusDTO;
import com.safe.safecampusbackend.model.vo.InfoListVO;
import com.safe.safecampusbackend.model.vo.InfoUserStatusVO;
import com.safe.safecampusbackend.model.vo.InfoVO;
import com.safe.safecampusbackend.service.InfoService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/handleInfo")
    public Result<String> handleInfo(@RequestBody InfoUserDTO infoUserDTO){
        return infoService.handleInfo(infoUserDTO);
    }

    @PostMapping("/getInfoUserStatus")
    public Result<InfoUserStatusVO> getInfoUserStatus(@RequestBody InfoUserStatusDTO infoUserStatusDTO){
        return infoService.getInfoUserStatus(infoUserStatusDTO);
    }
}
