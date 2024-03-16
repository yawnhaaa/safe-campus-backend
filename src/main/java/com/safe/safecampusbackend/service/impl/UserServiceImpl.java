package com.safe.safecampusbackend.service.impl;

import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.UserDao;
import com.safe.safecampusbackend.model.dto.LoginDTO;
import com.safe.safecampusbackend.model.dto.RegisterDTO;
import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.service.UserService;
import com.safe.safecampusbackend.util.Util;
import com.safe.safecampusbackend.util.jwt.JWTUtil;
import com.safe.safecampusbackend.util.redis.RedisUtil;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import com.safe.safecampusbackend.util.salt.SaltUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public Result<String> register(RegisterDTO registerDTO) {
        if (!registerDTO.getCode().equals(RedisUtil.getCache(registerDTO.getEmail()))) {
            return ResultUtil.error(-1, "验证码错误");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setName(registerDTO.getName());
        userEntity.setEmail(registerDTO.getEmail());
        String salt = SaltUtil.generateSalt();
        String passwd = SaltUtil.fixedSalt(registerDTO.getPasswd(), salt);
        userEntity.setPasswd(passwd);
        userEntity.setSalt(salt);
        try {
            userDao.insert(userEntity);
            return ResultUtil.success("注册成功");
        } catch (Exception e) {
            return ResultUtil.error(-1, "网络原因，注册失败");
        }
    }

    public Result<String> login(LoginDTO loginDTO) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", loginDTO.getEmail());
        UserEntity user = userDao.selectOne(queryWrapper);
        if (user == null) {
            return ResultUtil.error(-1, "不存在");
        }
        String passwd = loginDTO.getPasswd();
        if (SaltUtil.verifySalt(passwd, user.getSalt(), user.getPasswd())) {
            return ResultUtil.success(JWTUtil.createJWT(loginDTO.getEmail(), 3600000));
        } else {
            return ResultUtil.error(-1, "密码错误");
        }
    }

    public Result<String> getEmailCode(String email) {
        if (!Util.validateEmail(email)) {
            return ResultUtil.error(-1, "邮箱格式错误");
        }
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        UserEntity user = userDao.selectOne(queryWrapper);
        if (user != null) {
            return ResultUtil.error(-1, "该邮箱已注册");
        }
        String code = Util.getCode();
        RedisUtil.setCache(email, 300, code);
        MailUtil.send(email, "反诈校园验证码", "<html><body><h1>您的验证码为：" + code + "</h1><h4>五分钟内有效</h4></body></html>", true);
        return ResultUtil.success("验证码已发送至邮箱，有效期 5分钟");
    }
}
