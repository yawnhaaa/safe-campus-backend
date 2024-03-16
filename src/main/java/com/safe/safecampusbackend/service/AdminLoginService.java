package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.AdminLoginDTO;
import com.safe.safecampusbackend.util.result.Result;

public interface AdminLoginService {

    /**
     * 管理员登录获取 JWT
     *
     * @param adminLoginDTO 用户前端入参
     * @return JWT
     */
    Result<String> loginGetJWT(AdminLoginDTO adminLoginDTO);
}
