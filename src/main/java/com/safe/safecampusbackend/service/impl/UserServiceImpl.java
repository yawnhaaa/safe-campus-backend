package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.UserDao;
import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.service.UserService;
import com.safe.safecampusbackend.util.Util;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public List<UserEntity> getUser() {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        return userDao.selectList(queryWrapper);
    }

    public Result<String> getEmailCode(String email) {
        if (!Util.validateEmail(email)){
            return ResultUtil.error(-1, "邮箱格式错误");
        }

        return ResultUtil.success("验证码已发送至邮箱，有效期 5分钟");
    }
}
