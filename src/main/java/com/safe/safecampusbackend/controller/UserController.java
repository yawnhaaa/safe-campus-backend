package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.AdminLoginDTO;
import com.safe.safecampusbackend.model.dto.LoginDTO;
import com.safe.safecampusbackend.model.dto.RegisterDTO;
import com.safe.safecampusbackend.model.dto.UserDetailDTO;
import com.safe.safecampusbackend.model.vo.JWTVO;
import com.safe.safecampusbackend.model.vo.UserCollectVO;
import com.safe.safecampusbackend.model.vo.UserDetailVO;
import com.safe.safecampusbackend.model.vo.UserLikeVO;
import com.safe.safecampusbackend.service.AdminLoginService;
import com.safe.safecampusbackend.service.UserService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final AdminLoginService adminLoginService;

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping("/login")
    public Result<JWTVO> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PostMapping("/adminLogin")
    public Result<String> loginGetJWT(@RequestBody AdminLoginDTO adminLoginDTO) {
        return adminLoginService.loginGetJWT(adminLoginDTO);
    }

    @GetMapping("/protected/verifyUser")
    public Result<String> verifyUser() {
        return ResultUtil.success("200");
    }

    @GetMapping("/admin/verifyAdmin")
    public Result<String> verifyAdmin() {
        return ResultUtil.success("200");
    }

    @PostMapping("/getCode")
    public Result<String> getCode(@RequestBody HashMap<String, String> request) {
        return userService.getEmailCode(request.get("email"));
    }

    @GetMapping("/protected/getUserDetail/{id}")
    public Result<UserDetailVO> getUserDetail(@PathVariable Long id) {
        return userService.getUserDetail(id);
    }

    @PostMapping("/protected/updateUserDetail")
    public Result<String> updateUserDetail(@RequestBody UserDetailDTO userDetailDTO) {
        return userService.updateUserDetail(userDetailDTO);
    }

    @GetMapping("/getLikeList/{userId}")
    public Result<List<UserLikeVO>> getLikeList(@PathVariable Long userId) {
        return userService.getLikeList(userId);
    }

    @GetMapping("/getCollectList/{userId}")
    public Result<List<UserCollectVO>> getCollectList(@PathVariable Long userId) {
        return userService.getCollectList(userId);
    }
}
