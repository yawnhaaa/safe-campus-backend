package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.AdminLoginDTO;
import com.safe.safecampusbackend.model.dto.UserUpdatePasswdDTO;
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
}
