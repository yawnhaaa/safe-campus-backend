package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.AdminLoginDTO;
import com.safe.safecampusbackend.model.dto.QuestionDTO;
import com.safe.safecampusbackend.model.dto.UserUpdatePasswdDTO;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import com.safe.safecampusbackend.model.entity.MaterialEntity;
import com.safe.safecampusbackend.model.vo.QuestionListVO;
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

    @GetMapping("/info/{id}")
    public Result<InfoEntity> getInfoDetail(@PathVariable Long id) {
        return adminService.getInfoDetail(id);
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

    @GetMapping("/passInfo/{id}")
    public Result<String> passInfo(@PathVariable Long id) {
        return adminService.passInfo(id);
    }

    @GetMapping("/noPassInfo/{id}")
    public Result<String> noPassInfo(@PathVariable Long id) {
        return adminService.noPassInfo(id);
    }

    @GetMapping("/passInfoList")
    public Result<String> passInfoList(@RequestParam List<Long> idList) {
        return adminService.passInfoList(idList);
    }

    @GetMapping("/noPassInfoList")
    public Result<String> noPassInfoList(@RequestParam List<Long> idList) {
        return adminService.noPassInfoList(idList);
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

    @GetMapping("/material/{id}")
    public Result<MaterialEntity> getMaterialDetail(@PathVariable Long id) {
        return adminService.getMaterialDetail(id);
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

    @GetMapping("/passMaterial/{id}")
    public Result<String> passMaterial(@PathVariable Long id) {
        return adminService.passMaterial(id);
    }

    @GetMapping("/noPassMaterial/{id}")
    public Result<String> noPassMaterial(@PathVariable Long id) {
        return adminService.noPassMaterial(id);
    }

    @GetMapping("/passMaterialList")
    public Result<String> passMaterialList(@RequestParam List<Long> idList) {
        return adminService.passMaterialList(idList);
    }

    @GetMapping("/noPassMaterialList")
    public Result<String> noPassMaterialList(@RequestParam List<Long> idList) {
        return adminService.noPassMaterialList(idList);
    }

    @GetMapping("/getQuestionList")
    public Result<List<QuestionListVO>> getQuestionList() {
        return adminService.getQuestionList();
    }

    @GetMapping("/deleteQuestion/{id}")
    public Result<String> deleteQuestion(@PathVariable Long id) {
        return adminService.deleteQuestion(id);
    }

    @GetMapping("/deleteQuestionList")
    public Result<String> deleteQuestionList(@RequestParam List<Long> idList) {
        return adminService.deleteQuestionList(idList);
    }

    @PostMapping("/updateQuestion")
    public Result<String> updateQuestion(@RequestBody QuestionDTO questionDTO) {
        return adminService.updateQuestion(questionDTO);
    }

    @PostMapping("/newQuestion")
    public Result<String> newQuestion(@RequestBody QuestionDTO questionDTO) {
        return adminService.newQuestion(questionDTO);
    }
}
