package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.PoliceDTO;
import com.safe.safecampusbackend.model.entity.PoliceEntity;
import com.safe.safecampusbackend.model.vo.PoliceAdminListVO;
import com.safe.safecampusbackend.model.vo.PoliceListVO;
import com.safe.safecampusbackend.model.vo.PoliceVO;
import com.safe.safecampusbackend.service.PoliceService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PoliceController {
    private final PoliceService policeService;

    @GetMapping("/getPoliceById/{id}")
    public Result<PoliceVO> getPoliceById(@PathVariable Long id) {
        return policeService.getPoliceById(id);
    }

    @GetMapping("/getPoliceList")
    public Result<List<PoliceListVO>> getPoliceList() {
        return policeService.getPoliceList();
    }

    @GetMapping("/getAdminPoliceList")
    public Result<List<PoliceAdminListVO>> getAdminPoliceList() {
        return policeService.getAdminPoliceList();
    }

    @GetMapping("/banPoliceById/{id}")
    public Result<String> banPoliceById(@PathVariable Long id) {
        return policeService.banPoliceById(id);
    }

    @GetMapping("/noBanPoliceById/{id}")
    public Result<String> noBanPoliceById(@PathVariable Long id) {
        return policeService.noBanPoliceById(id);
    }

    @PostMapping("/newPolice")
    public Result<String> newPolice(@RequestBody PoliceDTO policeDTO) {
        return policeService.newPolice(policeDTO);
    }

    @PostMapping("/updatePolice")
    public Result<String> updatePolice(@RequestBody PoliceDTO policeDTO) {
        return policeService.updatePolice(policeDTO);
    }
}
