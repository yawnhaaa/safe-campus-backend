package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.UserUpdatePasswdDTO;
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

    /**
     * 更改用户密码
     *
     * @param userUpdatePasswdDTO 用户密码必要入参
     * @return 更改结果
     */
    Result<String> updateUserPasswd(UserUpdatePasswdDTO userUpdatePasswdDTO);

    /**
     * 启用用户
     *
     * @param id 用户id
     * @return 结果
     */
    Result<String> noBanUser(Long id);

    /**
     * 禁用用户
     *
     * @param id 用户id
     * @return 结果
     */
    Result<String> banUser(Long id);

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 结果
     */
    Result<String> deleteUser(Long id);

    /**
     * 批量启用用户
     *
     * @param idList 用户列表
     * @return 结果
     */
    Result<String> noBanUserList(List<Long> idList);

    /**
     * 批量禁用用户
     *
     * @param idList 用户列表
     * @return 结果
     */
    Result<String> banUserList(List<Long> idList);

    /**
     * 批量删除用户
     *
     * @param idList 用户列表
     * @return 结果
     */
    Result<String> deleteUserList(List<Long> idList);
}
