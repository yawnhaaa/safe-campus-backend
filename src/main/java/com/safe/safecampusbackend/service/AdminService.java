package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.vo.UserListAdminVO;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface AdminService {
    /**
     * 管理系统获取所有用户
     *
     * @return 用户列表
     */
    Result<List<UserListAdminVO>> getUserList();
}
