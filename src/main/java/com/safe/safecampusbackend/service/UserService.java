package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.LoginDTO;
import com.safe.safecampusbackend.model.dto.RegisterDTO;
import com.safe.safecampusbackend.model.dto.UserDetailDTO;
import com.safe.safecampusbackend.model.vo.JWTVO;
import com.safe.safecampusbackend.model.vo.UserCollectVO;
import com.safe.safecampusbackend.model.vo.UserDetailVO;
import com.safe.safecampusbackend.model.vo.UserLikeVO;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface UserService {
    /**
     * 用户注册
     *
     * @param registerDTO 用户前端表单
     * @return 注册结果
     */
    Result<String> register(RegisterDTO registerDTO);

    /**
     * 用户登录
     *
     * @param loginDTO 用户登录表单
     * @return 登录结果
     */
    Result<JWTVO> login(LoginDTO loginDTO);

    /**
     * 给注册邮箱发送邮件并返回发送情况
     *
     * @param email 用户邮箱
     * @return 发送情况
     */
    Result<String> getEmailCode(String email);

    /**
     * 获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    Result<UserDetailVO> getUserDetail(Long id);

    /**
     * 更新用户信息
     *
     * @param userDetailDTO 用户信息表单
     * @return 更新状态
     */
    Result<String> updateUserDetail(UserDetailDTO userDetailDTO);

    /**
     * 获取用户喜欢列表
     *
     * @param userId 用户id
     * @return 喜欢列表
     */
    Result<List<UserLikeVO>> getLikeList(Long userId);

    /**
     * 获取用户收藏列表
     *
     * @param userId 用户id
     * @return 收藏列表
     */
    Result<List<UserCollectVO>> getCollectList(Long userId);
}
