package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.service.UserService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/getCode")
    public Result<String> getCode(@RequestBody HashMap<String, String> request) {
        return userService.getEmailCode(request.get("email"));
    }
}
