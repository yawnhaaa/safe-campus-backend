package com.safe.safecampusbackend.service.impl;

import com.safe.safecampusbackend.dao.UserDAO;
import com.safe.safecampusbackend.model.dto.UserUpdatePasswdDTO;
import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.model.vo.UserListAdminVO;
import com.safe.safecampusbackend.service.AdminService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserDAO userDAO;

    public Result<List<UserListAdminVO>> getUserList() {
        List<UserEntity> userList = userDAO.selectList(null);
        List<UserListAdminVO> userListAdminVO = userList.stream()
                .map(user -> {
                    UserListAdminVO userListAdminVOItem = new UserListAdminVO();
                    BeanUtils.copyProperties(user, userListAdminVOItem);

                    return userListAdminVOItem;
                })
                .toList();
        return ResultUtil.success(userListAdminVO);
    }

    public Result<String> updateUserPasswd(UserUpdatePasswdDTO userUpdatePasswdDTO) {
        // 从DTO对象中获取ID和新密码
        Long userId = userUpdatePasswdDTO.getId();
        String newPassword = userUpdatePasswdDTO.getPasswd();

        // 查询数据库获取对应用户信息
        UserEntity existingUser = userDAO.selectById(userId);

        if (existingUser != null) {
            // 更新密码字段
            existingUser.setPasswd(newPassword);
            // 调用MyBatis Plus的更新方法来更新用户信息
            boolean updateResult = userDAO.updateById(existingUser) > 0;

            if (updateResult) {
                return ResultUtil.success("更改成功");
            } else {
                return ResultUtil.error(-1, "网络错误");
            }
        } else {
            return ResultUtil.error(-1, "网络错误");
        }
    }

    public Result<String> noBanUser(Long id) {
        UserEntity user = userDAO.selectById(id);
        if (user.getIsDelete().equals(0)) {
            return ResultUtil.error(-1, "启用状态");
        }
        user.setIsDelete(0);
        userDAO.updateById(user);
        return ResultUtil.success("启用成功");
    }

    public Result<String> banUser(Long id) {
        UserEntity user = userDAO.selectById(id);
        if (user.getIsDelete().equals(1)) {
            return ResultUtil.error(-1, "禁用状态");
        }
        user.setIsDelete(1);
        userDAO.updateById(user);
        return ResultUtil.success("禁用成功");
    }

    public Result<String> deleteUser(Long id) {
        userDAO.deleteById(id);
        return ResultUtil.success("删除成功");
    }

    public Result<String> noBanUserList(List<Long> idList) {
        for (Long id : idList) {
            UserEntity user = userDAO.selectById(id);
            user.setIsDelete(0);
            userDAO.updateById(user);
        }
        return ResultUtil.success("启用成功");
    }

    public Result<String> banUserList(List<Long> idList) {
        for (Long id : idList) {
            UserEntity user = userDAO.selectById(id);
            user.setIsDelete(1);
            userDAO.updateById(user);
        }
        return ResultUtil.success("禁用成功");
    }

    public Result<String> deleteUserList(List<Long> idList) {
        for (Long id : idList) {
            userDAO.deleteById(id);
        }
        return ResultUtil.success("删除成功");
    }
}
