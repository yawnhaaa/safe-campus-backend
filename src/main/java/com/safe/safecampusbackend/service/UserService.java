package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.LoginDTO;
import com.safe.safecampusbackend.model.dto.RegisterDTO;
import com.safe.safecampusbackend.util.result.Result;

public interface UserService {
    /**
     * 用户注册
     *
     * @param registerDTO 用户前端表单
     * @return 注册结果
     */
    Result<String> register(RegisterDTO registerDTO);

    /**
     * 用户登录
     *
     * @param loginDTO 用户登录表单
     * @return 登录结果
     */
    Result<String> login(LoginDTO loginDTO);

    /**
     * 给注册邮箱发送邮件并返回发送情况
     *
     * @param email 用户邮箱
     * @return 发送情况
     */
    Result<String> getEmailCode(String email);
}
