package com.safe.safecampusbackend.controller;

import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/getUser")
    public List<UserEntity> getUser() {
        return userService.getUser();
    }
}
