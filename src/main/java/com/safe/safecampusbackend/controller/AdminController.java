package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.service.AdminLoginService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminLoginService adminLoginService;
    @GetMapping("/login")
    public Result<String> loginGetJWT(){
        return adminLoginService.loginGetJWT();
    }
}
