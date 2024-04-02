package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.LoginDTO;
import com.safe.safecampusbackend.model.dto.RegisterDTO;
import com.safe.safecampusbackend.model.dto.UserDetailDTO;
import com.safe.safecampusbackend.model.vo.JWTVO;
import com.safe.safecampusbackend.model.vo.UserCollectVO;
import com.safe.safecampusbackend.model.vo.UserDetailVO;
import com.safe.safecampusbackend.model.vo.UserLikeVO;
import com.safe.safecampusbackend.service.UserService;
import com.safe.safecampusbackend.util.jwt.JWTUtil;
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

    @PostMapping("/register")
    public Result<String> register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping("/login")
    public Result<JWTVO> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PostMapping("/getCode")
    public Result<String> getCode(@RequestBody HashMap<String, String> request) {
        return userService.getEmailCode(request.get("email"));
    }

    @PostMapping("/verifyUser")
    public Result<Boolean> verifyUser(@RequestBody HashMap<String, String> request) {
        String jwt = request.get("jwt");
        String stuId = request.get("stuId");
        return ResultUtil.success(JWTUtil.validateJWT(jwt, stuId));
    }

    @GetMapping("/getUserDetail/{id}")
    public Result<UserDetailVO> getUserDetail(@PathVariable Long id) {
        return userService.getUserDetail(id);
    }

    @PostMapping("/updateUserDetail")
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
