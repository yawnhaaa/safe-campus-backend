package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.UserDao;
import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.service.UserService;
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
}
