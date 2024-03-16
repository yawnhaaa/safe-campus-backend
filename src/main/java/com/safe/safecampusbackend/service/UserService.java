package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface UserService {
    List<UserEntity> getUser();

    /**
     * 给注册邮箱发送邮件并返回发送情况
     *
     * @param email 用户邮箱
     * @return 发送情况
     */
    Result<String> getEmailCode(String email);
}
