package com.safe.safecampusbackend.service.impl;

import com.safe.safecampusbackend.service.AdminLoginService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminLoginServiceImpl implements AdminLoginService {
    public Result<String> loginGetJWT(){
        return ResultUtil.success("Hello");
    }
}
