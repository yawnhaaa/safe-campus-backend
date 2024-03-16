package com.safe.safecampusbackend.service.impl;

import cn.hutool.extra.mail.MailUtil;
import com.safe.safecampusbackend.dao.UserDao;
import com.safe.safecampusbackend.service.UserService;
import com.safe.safecampusbackend.util.Util;
import com.safe.safecampusbackend.util.redis.RedisUtil;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public Result<String> getEmailCode(String email) {
        if (!Util.validateEmail(email)) {
            return ResultUtil.error(-1, "邮箱格式错误");
        }
        String code = Util.getCode();
        RedisUtil.setCache(email, 300, code);
        MailUtil.send(email, "反诈校园验证码", "<html><body><h1>您的验证码为：" + code + "</h1><h4>五分钟内有效</h4></body></html>", true);
        return ResultUtil.success("验证码已发送至邮箱，有效期 5分钟");
    }
}
