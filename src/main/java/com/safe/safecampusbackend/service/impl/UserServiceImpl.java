package com.safe.safecampusbackend.service.impl;

import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.InfoDAO;
import com.safe.safecampusbackend.dao.InfoUserDAO;
import com.safe.safecampusbackend.dao.UserDAO;
import com.safe.safecampusbackend.model.dto.LoginDTO;
import com.safe.safecampusbackend.model.dto.RegisterDTO;
import com.safe.safecampusbackend.model.dto.UserDetailDTO;
import com.safe.safecampusbackend.model.entity.InfoEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDAO userDao;
    private final InfoDAO infoDAO;
    private final InfoUserDAO infoUserDAO;

    public Result<String> register(RegisterDTO registerDTO) {
        QueryWrapper<UserEntity> stuIdQueryWrapper = new QueryWrapper<>();
        stuIdQueryWrapper.eq("stu_id", registerDTO.getEmail());
        UserEntity stuIdUser = userDao.selectOne(stuIdQueryWrapper);
        // 异常处理
        if (stuIdUser != null) {
            return ResultUtil.error(-1, "该学号已注册");
        }
        QueryWrapper<UserEntity> emailQueryWrapper = new QueryWrapper<>();
        emailQueryWrapper.eq("email", registerDTO.getEmail());
        UserEntity emailUser = userDao.selectOne(emailQueryWrapper);
        // 异常处理
        if (emailUser != null) {
            return ResultUtil.error(-1, "该邮箱已注册");
        }
        // 异常处理
        if (!registerDTO.getCode().equals(RedisUtil.getCache(registerDTO.getEmail()))) {
            return ResultUtil.error(-1, "验证码错误");
        }
        // 初始化用户类对象
        UserEntity userEntity = new UserEntity();
        userEntity.setStuId(registerDTO.getStuId());
        userEntity.setName(registerDTO.getName());
        userEntity.setEmail(registerDTO.getEmail());
        String salt = SaltUtil.generateSalt();
        String passwd = SaltUtil.fixedSalt(registerDTO.getPasswd(), salt);
        userEntity.setPasswd(passwd);
        userEntity.setSalt(salt);
        try {
            // 数据入库
            userDao.insert(userEntity);
            return ResultUtil.success("注册成功");
        } catch (Exception e) {
            return ResultUtil.error(-1, "网络原因，注册失败");
        }
    }

    public Result<JWTVO> login(LoginDTO loginDTO) {
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stu_id", loginDTO.getStuId());
        // 找到该用户
        UserEntity user = userDao.selectOne(queryWrapper);
        // 异常处理
        if (user == null) {
            return ResultUtil.error(-1, "该用户不存在");
        }
        // 异常处理
        if (user.getIsDelete().equals(1)) {
            return ResultUtil.error(-1, "该用户被禁用，请联系系统管理员");
        }
        String passwd = loginDTO.getPasswd();
        // 用户入参密码混合盐与加密密码对比
        if (SaltUtil.verifySalt(passwd, user.getSalt(), user.getPasswd())) {
            JWTVO jwtvo = new JWTVO();
            jwtvo.setJwt(JWTUtil.createJWT(loginDTO.getStuId(), 3600000));
            jwtvo.setName(user.getName());
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

    public Result<List<UserLikeVO>> getLikeList(Long userId) {
        QueryWrapper<InfoUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .eq("is_like", 1);
        List<InfoUserEntity> infoUserEntityList = infoUserDAO.selectList(queryWrapper);
        List<UserLikeVO> userLikeVOList = new ArrayList<>();
        for (InfoUserEntity infoUserEntity : infoUserEntityList) {
            if (infoUserEntity != null) {
                UserLikeVO userLikeVO = new UserLikeVO();
                InfoEntity infoEntity = infoDAO.selectById(infoUserEntity.getInfoId());
                BeanUtils.copyProperties(infoEntity, userLikeVO);
                userLikeVOList.add(userLikeVO);
            }
        }
        return ResultUtil.success(userLikeVOList);
    }

    public Result<List<UserCollectVO>> getCollectList(Long userId) {
        QueryWrapper<InfoUserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("user_id", userId)
                .eq("is_collect", 1);
        List<InfoUserEntity> infoUserEntityList = infoUserDAO.selectList(queryWrapper);
        List<UserCollectVO> userCollectVOList = new ArrayList<>();
        for (InfoUserEntity infoUserEntity : infoUserEntityList) {
            if (infoUserEntity != null) {
                UserCollectVO userCollectVO = new UserCollectVO();
                InfoEntity infoEntity = infoDAO.selectById(infoUserEntity.getInfoId());
                BeanUtils.copyProperties(infoEntity, userCollectVO);
                userCollectVOList.add(userCollectVO);
            }
        }
        return ResultUtil.success(userCollectVOList);
    }
}
