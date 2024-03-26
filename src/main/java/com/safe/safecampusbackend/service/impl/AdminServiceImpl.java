package com.safe.safecampusbackend.service.impl;

import com.safe.safecampusbackend.dao.UserDAO;
import com.safe.safecampusbackend.model.vo.UserListAdminVO;
import com.safe.safecampusbackend.service.AdminService;
import com.safe.safecampusbackend.util.result.Result;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserDAO userDAO;

    public Result<List<UserListAdminVO>> getUserList(){

    }
}
