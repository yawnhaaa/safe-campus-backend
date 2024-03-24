package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.IssueMaterialDTO;
import com.safe.safecampusbackend.model.vo.MaterialListVO;
import com.safe.safecampusbackend.model.vo.MaterialVO;
import com.safe.safecampusbackend.service.MaterialService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping("/imageList")
    public Result<List<MaterialListVO>> getImageList() {
        return materialService.getImageList();
    }

    @GetMapping("/videoList")
    public Result<List<MaterialListVO>> getVideoList() {
        return materialService.getVideoList();
    }

    @GetMapping("/audioList")
    public Result<List<MaterialListVO>> getAudioList() {
        return materialService.getAudioList();
    }

    @GetMapping("/material/{id}")
    public Result<MaterialVO> getMaterial(@PathVariable Long id) {
        return materialService.getMaterial(id);
    }

    @GetMapping("/downloadMaterial/{id}")
    public Result<String> downloadMaterial(@PathVariable Long id) {
        return materialService.downloadMaterial(id);
    }

    @PostMapping("/issueMaterial")
    public Result<String> issueMaterial(IssueMaterialDTO issueMaterialDTO) {
        return materialService.issueMaterial(issueMaterialDTO);
    }
}
