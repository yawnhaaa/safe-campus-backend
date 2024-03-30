package com.safe.safecampusbackend.service.impl;

import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.UserDAO;
import com.safe.safecampusbackend.model.dto.LoginDTO;
import com.safe.safecampusbackend.model.dto.RegisterDTO;
import com.safe.safecampusbackend.model.dto.UserDetailDTO;
import com.safe.safecampusbackend.model.entity.InfoUserEntity;
import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.model.vo.JWTVO;
import com.safe.safecampusbackend.model.vo.UserCollectVO;
import com.safe.safecampusbackend.model.vo.UserDetailVO;
import com.safe.safecampusbackend.model.vo.UserLikeVO;
import com.safe.safecampusbackend.service.UserService;
import com.safe.safecampusbackend.util.Util;
import com.safe.safecampusbackend.util.jwt.JWTUtil;
import com.safe.safecampusbackend.util.redis.RedisUtil;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import com.safe.safecampusbackend.util.salt.SaltUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDao;

    public Result<String> register(RegisterDTO registerDTO) {
        QueryWrapper<UserEntity> emailQueryWrapper = new QueryWrapper<>();
        emailQueryWrapper.eq("email", registerDTO.getEmail());
        UserEntity emailUser = userDao.selectOne(emailQueryWrapper);
        if (emailUser != null) {
            return ResultUtil.error(-1, "该邮箱已注册");
        }
        QueryWrapper<UserEntity> nameQueryWrapper = new QueryWrapper<>();
        nameQueryWrapper.eq("name", registerDTO.getName());
        UserEntity nameUser = userDao.selectOne(nameQueryWrapper);
        if (nameUser != null) {
            return ResultUtil.error(-1, "该昵称已使用");
        }
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

    public Result<JWTVO> login(LoginDTO loginDTO) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", loginDTO.getName());
        UserEntity user = userDao.selectOne(queryWrapper);
        if (user == null) {
            return ResultUtil.error(-1, "该用户不存在");
        }
        if (user.getIsDelete().equals(1)) {
            return ResultUtil.error(-1, "该用户被禁用，请联系系统管理员");
        }
        String passwd = loginDTO.getPasswd();
        if (SaltUtil.verifySalt(passwd, user.getSalt(), user.getPasswd())) {
            JWTVO jwtvo = new JWTVO();
            jwtvo.setJwt(JWTUtil.createJWT(loginDTO.getName(), 3600000));
            jwtvo.setUserName(user.getName());
            jwtvo.setUserId(user.getId());
            return ResultUtil.success(jwtvo);
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

    public Result<UserDetailVO> getUserDetail(Long id) {
        UserEntity userEntity = userDao.selectById(id);
        UserDetailVO userDetailVO = new UserDetailVO();
        if (userEntity != null && userEntity.getIsDelete() != 1) {
            BeanUtils.copyProperties(userEntity, userDetailVO);
        }
        return ResultUtil.success(userDetailVO);
    }

    public Result<String> updateUserDetail(UserDetailDTO userDetailDTO) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", userDetailDTO.getName());
        UserEntity userEntity = userDao.selectOne(queryWrapper);
        if (userEntity != null && (!Objects.equals(userDetailDTO.getId(), userEntity.getId()))) {
            return ResultUtil.error(-1, "该昵称已被使用");
        }
        UserEntity entity = userDao.selectById(userDetailDTO.getId());
        if (entity == null) {
            return ResultUtil.error(-1, "网络错误");
        }
        BeanUtils.copyProperties(userDetailDTO, entity);
        try {
            userDao.updateById(entity);
            return ResultUtil.success("更新成功");
        } catch (Exception e) {
            return ResultUtil.error(-1, "网络错误");
        }
    }

    public Result<UserLikeVO> getLikeList(String username) {
        QueryWrapper<InfoUserEntity> queryWrapper = new QueryWrapper<>();
    }

    public Result<UserCollectVO> getCollectList(String username) {

    }
}
