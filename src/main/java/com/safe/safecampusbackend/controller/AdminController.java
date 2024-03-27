package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.AdminLoginDTO;
import com.safe.safecampusbackend.model.dto.UserUpdatePasswdDTO;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import com.safe.safecampusbackend.model.entity.MaterialEntity;
import com.safe.safecampusbackend.model.vo.UserListAdminVO;
import com.safe.safecampusbackend.service.AdminLoginService;
import com.safe.safecampusbackend.service.AdminService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminLoginService adminLoginService;
    private final AdminService adminService;

    @PostMapping("/aLogin")
    public Result<String> loginGetJWT(@RequestBody AdminLoginDTO adminLoginDTO) {
        return adminLoginService.loginGetJWT(adminLoginDTO);
    }

    @GetMapping("/getUserList")
    public Result<List<UserListAdminVO>> getUserList() {
        return adminService.getUserList();
    }

    @PostMapping("/updateUserPasswd")
    public Result<String> updateUserPasswd(@RequestBody UserUpdatePasswdDTO userUpdatePasswdDTO) {
        return adminService.updateUserPasswd(userUpdatePasswdDTO);
    }

    @GetMapping("/noBanUser/{id}")
    public Result<String> noBanUser(@PathVariable Long id) {
        return adminService.noBanUser(id);
    }

    @GetMapping("/banUser/{id}")
    public Result<String> banUser(@PathVariable Long id) {
        return adminService.banUser(id);
    }

    @GetMapping("/deleteUser/{id}")
    public Result<String> deleteUser(@PathVariable Long id) {
        return adminService.deleteUser(id);
    }

    @GetMapping("/noBanUserList")
    public Result<String> noBanUserList(@RequestParam List<Long> idList) {
        return adminService.noBanUserList(idList);
    }

    @GetMapping("/banUserList")
    public Result<String> banUserList(@RequestParam List<Long> idList) {
        return adminService.banUserList(idList);
    }

    @GetMapping("/deleteUserList")
    public Result<String> deleteUserList(@RequestParam List<Long> idList) {
        return adminService.deleteUserList(idList);
    }

    @GetMapping("/getInfoList")
    public Result<List<InfoEntity>> getInfoList() {
        return adminService.getInfoList();
    }

    @GetMapping("/noBanInfo/{id}")
    public Result<String> noBanInfo(@PathVariable Long id) {
        return adminService.noBanInfo(id);
    }

    @GetMapping("/banInfo/{id}")
    public Result<String> banInfo(@PathVariable Long id) {
        return adminService.banInfo(id);
    }

    @GetMapping("/deleteInfo/{id}")
    public Result<String> deleteInfo(@PathVariable Long id) {
        return adminService.deleteInfo(id);
    }

    @GetMapping("/noBanInfoList")
    public Result<String> noBanInfoList(@RequestParam List<Long> idList) {
        return adminService.noBanInfoList(idList);
    }

    @GetMapping("/banInfoList")
    public Result<String> banInfoList(@RequestParam List<Long> idList) {
        return adminService.banInfoList(idList);
    }

    @GetMapping("/deleteInfoList")
    public Result<String> deleteInfoList(@RequestParam List<Long> idList) {
        return adminService.deleteInfoList(idList);
    }

    @GetMapping("/getInfoReviewList")
    public Result<List<InfoEntity>> getInfoReviewList() {
        return adminService.getInfoReviewList();
    }

    @GetMapping("/reviewInfo/{id}")
    public Result<String> reviewInfo(@PathVariable Long id) {
        return adminService.reviewInfo(id);
    }

    @GetMapping("/reviewInfoList")
    public Result<String> reviewInfoList(@RequestParam List<Long> idList) {
        return adminService.reviewInfoList(idList);
    }

    @GetMapping("/getImageList")
    public Result<List<MaterialEntity>> getImageList() {
        return adminService.getImageList();
    }

    @GetMapping("/getVideoList")
    public Result<List<MaterialEntity>> getVideoList() {
        return adminService.getVideoList();
    }

    @GetMapping("/getAudioList")
    public Result<List<MaterialEntity>> getAudioList() {
        return adminService.getAudioList();
    }

    @GetMapping("/noBanMaterial/{id}")
    public Result<String> noBanMaterial(@PathVariable Long id) {
        return adminService.noBanMaterial(id);
    }

    @GetMapping("/banMaterial/{id}")
    public Result<String> banMaterial(@PathVariable Long id) {
        return adminService.banMaterial(id);
    }

    @GetMapping("/deleteMaterial/{id}")
    public Result<String> deleteMaterial(@PathVariable Long id) {
        return adminService.deleteMaterial(id);
    }

    @GetMapping("/noBanMaterialList")
    public Result<String> noBanMaterialList(@RequestParam List<Long> idList) {
        return adminService.noBanMaterialList(idList);
    }

    @GetMapping("/banMaterialList")
    public Result<String> banMaterialList(@RequestParam List<Long> idList) {
        return adminService.banMaterialList(idList);
    }

    @GetMapping("/deleteMaterialList")
    public Result<String> deleteMaterialList(@RequestParam List<Long> idList) {
        return adminService.deleteMaterialList(idList);
    }

    @GetMapping("/getImageReviewList")
    public Result<List<MaterialEntity>> getImageReviewList() {
        return adminService.getImageReviewList();
    }

    @GetMapping("/getVideoReviewList")
    public Result<List<MaterialEntity>> getVideoReviewList() {
        return adminService.getVideoReviewList();
    }

    @GetMapping("/getAudioReviewList")
    public Result<List<MaterialEntity>> getAudioReviewList() {
        return adminService.getAudioReviewList();
    }

    @GetMapping("/reviewMaterial/{id}")
    public Result<String> reviewMaterial(@PathVariable Long id) {
        return adminService.reviewMaterial(id);
    }

    @GetMapping("/reviewMaterialList")
    public Result<String> reviewMaterialList(@RequestParam List<Long> idList) {
        return adminService.reviewMaterialList(idList);
    }
}
