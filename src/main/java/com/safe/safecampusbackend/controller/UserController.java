package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.LoginDTO;
import com.safe.safecampusbackend.model.dto.RegisterDTO;
import com.safe.safecampusbackend.service.UserService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("register")
    public Result<String> register(@RequestBody RegisterDTO registerDTO) {
        return userService.register(registerDTO);
    }

    @PostMapping("login")
    public Result<String> login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    @PostMapping("/getCode")
    public Result<String> getCode(@RequestBody HashMap<String, String> request) {
        return userService.getEmailCode(request.get("email"));
    }
}
