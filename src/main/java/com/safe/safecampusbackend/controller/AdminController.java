package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.dto.AdminLoginDTO;
import com.safe.safecampusbackend.service.AdminLoginService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminLoginService adminLoginService;

    @PostMapping("/aLogin")
    public Result<String> loginGetJWT(@RequestBody AdminLoginDTO adminLoginDTO) {
        return adminLoginService.loginGetJWT(adminLoginDTO);
    }
}
