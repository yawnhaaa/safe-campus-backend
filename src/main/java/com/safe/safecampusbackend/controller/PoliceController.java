package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.PoliceDTO;
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

    @GetMapping("/admin/getAdminPoliceList")
    public Result<List<PoliceAdminListVO>> getAdminPoliceList() {
        return policeService.getAdminPoliceList();
    }

    @GetMapping("/admin/banPoliceById/{id}")
    public Result<String> banPoliceById(@PathVariable Long id) {
        return policeService.banPoliceById(id);
    }

    @GetMapping("/admin/noBanPoliceById/{id}")
    public Result<String> noBanPoliceById(@PathVariable Long id) {
        return policeService.noBanPoliceById(id);
    }

    @GetMapping("/admin/deletePoliceById/{id}")
    public Result<String> deletePoliceById(@PathVariable Long id) {
        return policeService.deletePoliceById(id);
    }

    @GetMapping("/admin/noBanPoliceList")
    public Result<String> noBanPoliceList(@RequestParam List<Long> idList) {
        return policeService.noBanPoliceList(idList);
    }

    @GetMapping("/admin/banPoliceList")
    public Result<String> banPoliceList(@RequestParam List<Long> idList) {
        return policeService.banPoliceList(idList);
    }

    @GetMapping("/admin/deletePoliceList")
    public Result<String> deletePoliceList(@RequestParam List<Long> idList) {
        return policeService.deletePoliceList(idList);
    }

    @PostMapping("/admin/newPolice")
    public Result<String> newPolice(@RequestBody PoliceDTO policeDTO) {
        return policeService.newPolice(policeDTO);
    }

    @PostMapping("/admin/updatePolice")
    public Result<String> updatePolice(@RequestBody PoliceDTO policeDTO) {
        return policeService.updatePolice(policeDTO);
    }
}
