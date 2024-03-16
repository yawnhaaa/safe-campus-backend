package com.safe.safecampusbackend.service.impl;

import com.safe.safecampusbackend.dao.AdminDAO;
import com.safe.safecampusbackend.model.dto.AdminLoginDTO;
import com.safe.safecampusbackend.model.entity.AdminEntity;
import com.safe.safecampusbackend.service.AdminLoginService;
import com.safe.safecampusbackend.util.jwt.JWTUtil;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import com.safe.safecampusbackend.util.salt.SaltUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminLoginServiceImpl implements AdminLoginService {
    private final AdminDAO adminDAO;

    public Result<String> loginGetJWT(AdminLoginDTO adminLoginDTO) {
        if (!adminLoginDTO.getUsername().equals("admin")) {
            return ResultUtil.error(-1, "无此管理员用户");
        }
        AdminEntity adminEntity = adminDAO.selectById(1);
        if (SaltUtil.verifySalt(adminLoginDTO.getPasswd(), adminEntity.getSalt(), adminEntity.getPasswd())) {
            return ResultUtil.success(JWTUtil.createJWT("admin", 3600000));
        } else {
            return ResultUtil.error(-1, "密码错误");
        }
    }
}
