package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.InfoDAO;
import com.safe.safecampusbackend.dao.MaterialDAO;
import com.safe.safecampusbackend.dao.QuestionDAO;
import com.safe.safecampusbackend.dao.UserDAO;
import com.safe.safecampusbackend.model.dto.UserUpdatePasswdDTO;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import com.safe.safecampusbackend.model.entity.MaterialEntity;
import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.model.vo.UserListAdminVO;
import com.safe.safecampusbackend.service.AdminService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import com.safe.safecampusbackend.util.salt.SaltUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserDAO userDAO;
    private final InfoDAO infoDAO;
    private final MaterialDAO materialDAO;
    private final QuestionDAO questionDAO;

    // user逻辑开始
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

        // 查询数据库获取对应用户信息
        UserEntity existingUser = userDAO.selectById(userId);

        if (existingUser != null) {
            // 更新密码字段
            String salt = SaltUtil.generateSalt();
            String passwd = SaltUtil.fixedSalt(userUpdatePasswdDTO.getPasswd(), salt);
            existingUser.setPasswd(passwd);
            existingUser.setSalt(salt);

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
        if (user == null) return ResultUtil.error(-1, "网络错误");
        if (user.getIsDelete().equals(0)) {
            return ResultUtil.error(-1, "启用状态");
        }
        user.setIsDelete(0);
        userDAO.updateById(user);
        return ResultUtil.success("启用成功");
    }

    public Result<String> banUser(Long id) {
        UserEntity user = userDAO.selectById(id);
        if (user == null) return ResultUtil.error(-1, "网络错误");
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
            if (user == null) return ResultUtil.error(-1, "网络错误");
            user.setIsDelete(0);
            userDAO.updateById(user);
        }
        return ResultUtil.success("启用成功");
    }

    public Result<String> banUserList(List<Long> idList) {
        for (Long id : idList) {
            UserEntity user = userDAO.selectById(id);
            if (user == null) return ResultUtil.error(-1, "网络错误");
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

    // info逻辑开始
    public Result<List<InfoEntity>> getInfoList() {
        QueryWrapper<InfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete", 0, 1);
        List<InfoEntity> infoEntityList = infoDAO.selectList(queryWrapper);
        return ResultUtil.success(infoEntityList);
    }

    public Result<InfoEntity> getInfoDetail(Long id) {
        InfoEntity info = infoDAO.selectById(id);
        if (info != null) {
            return ResultUtil.success(info);
        } else {
            return ResultUtil.error(-1, "网络错误");
        }
    }

    public Result<String> noBanInfo(Long id) {
        InfoEntity info = infoDAO.selectById(id);
        if (info == null) return ResultUtil.error(-1, "网络错误");
        if (info.getIsDelete().equals(0)) {
            return ResultUtil.error(-1, "启用状态");
        }
        info.setIsDelete(0);
        infoDAO.updateById(info);
        return ResultUtil.success("启用成功");
    }

    public Result<String> banInfo(Long id) {
        InfoEntity info = infoDAO.selectById(id);
        if (info == null) return ResultUtil.error(-1, "网络错误");
        if (info.getIsDelete().equals(1)) {
            return ResultUtil.error(-1, "限流状态");
        }
        info.setIsDelete(1);
        infoDAO.updateById(info);
        return ResultUtil.success("限流成功");
    }

    public Result<String> deleteInfo(Long id) {
        infoDAO.deleteById(id);
        return ResultUtil.success("删除成功");
    }

    public Result<String> noBanInfoList(List<Long> idList) {
        for (Long id : idList) {
            InfoEntity info = infoDAO.selectById(id);
            if (info == null) return ResultUtil.error(-1, "网络错误");
            info.setIsDelete(0);
            infoDAO.updateById(info);
        }
        return ResultUtil.success("启用成功");
    }

    public Result<String> banInfoList(List<Long> idList) {
        for (Long id : idList) {
            InfoEntity info = infoDAO.selectById(id);
            if (info == null) return ResultUtil.error(-1, "网络错误");
            info.setIsDelete(1);
            infoDAO.updateById(info);
        }
        return ResultUtil.success("限流成功");
    }

    public Result<String> deleteInfoList(List<Long> idList) {
        for (Long id : idList) {
            infoDAO.deleteById(id);
        }
        return ResultUtil.success("删除成功");
    }

    public Result<List<InfoEntity>> getInfoReviewList() {
        QueryWrapper<InfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete", 2, 3);
        List<InfoEntity> infoEntityList = infoDAO.selectList(queryWrapper);
        return ResultUtil.success(infoEntityList);
    }

    public Result<String> passInfo(Long id) {
        InfoEntity info = infoDAO.selectById(id);
        if (info == null) return ResultUtil.error(-1, "网络错误");
        if (info.getIsDelete().equals(0)) {
            return ResultUtil.error(-1, "启用状态");
        }
        info.setIsDelete(0);
        infoDAO.updateById(info);
        return ResultUtil.success("审核通过");
    }

    public Result<String> noPassInfo(Long id) {
        InfoEntity info = infoDAO.selectById(id);
        if (info == null) return ResultUtil.error(-1, "网络错误");
        if (info.getIsDelete().equals(3)) {
            return ResultUtil.error(-1, "驳回状态");
        }
        info.setIsDelete(3);
        infoDAO.updateById(info);
        return ResultUtil.success("审核驳回");
    }

    public Result<String> passInfoList(List<Long> idList) {
        for (Long id : idList) {
            InfoEntity info = infoDAO.selectById(id);
            if (info == null) return ResultUtil.error(-1, "网络错误");
            info.setIsDelete(0);
            infoDAO.updateById(info);
        }
        return ResultUtil.success("启用成功");
    }

    public Result<String> noPassInfoList(List<Long> idList) {
        for (Long id : idList) {
            InfoEntity info = infoDAO.selectById(id);
            if (info == null) return ResultUtil.error(-1, "网络错误");
            info.setIsDelete(3);
            infoDAO.updateById(info);
        }
        return ResultUtil.success("驳回成功");
    }


    public Result<List<MaterialEntity>> getImageList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete", 0, 1);
        queryWrapper.eq("material_type", 0); // 添加material_type条件
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        return ResultUtil.success(materialEntityList);
    }

    public Result<List<MaterialEntity>> getVideoList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete", 0, 1);
        queryWrapper.eq("material_type", 1); // 添加material_type条件
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        return ResultUtil.success(materialEntityList);
    }

    public Result<List<MaterialEntity>> getAudioList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete", 0, 1);
        queryWrapper.eq("material_type", 2); // 添加material_type条件
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        return ResultUtil.success(materialEntityList);
    }

    public Result<MaterialEntity> getMaterialDetail(Long id) {
        MaterialEntity material = materialDAO.selectById(id);
        if (material != null) {
            return ResultUtil.success(material);
        } else {
            return ResultUtil.error(-1, "网络错误");
        }
    }

    public Result<String> noBanMaterial(Long id) {
        MaterialEntity material = materialDAO.selectById(id);
        if (material == null) return ResultUtil.error(-1, "网络错误");
        if (material.getIsDelete().equals(0)) {
            return ResultUtil.error(-1, "启用状态");
        }
        material.setIsDelete(0);
        materialDAO.updateById(material);
        return ResultUtil.success("启用成功");
    }

    public Result<String> noBanMaterialList(List<Long> idList) {
        for (Long id : idList) {
            MaterialEntity material = materialDAO.selectById(id);
            if (material == null) return ResultUtil.error(-1, "网络错误");
            material.setIsDelete(0);
            materialDAO.updateById(material);
        }
        return ResultUtil.success("启动成功");
    }

    public Result<String> banMaterial(Long id) {
        MaterialEntity material = materialDAO.selectById(id);
        if (material == null) return ResultUtil.error(-1, "网络错误");
        if (material.getIsDelete().equals(1)) {
            return ResultUtil.error(-1, "限流状态");
        }
        material.setIsDelete(1);
        materialDAO.updateById(material);
        return ResultUtil.success("限流成功");
    }


    public Result<String> banMaterialList(List<Long> idList) {
        for (Long id : idList) {
            MaterialEntity material = materialDAO.selectById(id);
            if (material == null) return ResultUtil.error(-1, "网络错误");
            material.setIsDelete(1);
            materialDAO.updateById(material);
        }
        return ResultUtil.success("限流成功");
    }

    public Result<String> deleteMaterial(Long id) {
        materialDAO.deleteById(id);
        return ResultUtil.success("删除成功");
    }

    public Result<String> deleteMaterialList(List<Long> idList) {
        for (Long id : idList) {
            materialDAO.deleteById(id);
        }
        return ResultUtil.success("删除成功");
    }

    public Result<List<MaterialEntity>> getImageReviewList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete", 2, 3);
        ;
        queryWrapper.eq("material_type", 0); // 添加material_type条件
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        return ResultUtil.success(materialEntityList);
    }

    public Result<List<MaterialEntity>> getVideoReviewList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete", 2, 3);
        ;
        queryWrapper.eq("material_type", 1); // 添加material_type条件
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        return ResultUtil.success(materialEntityList);
    }

    public Result<List<MaterialEntity>> getAudioReviewList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete", 2, 3);
        queryWrapper.eq("material_type", 2); // 添加material_type条件
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        return ResultUtil.success(materialEntityList);
    }

    public Result<String> passMaterial(Long id) {
        MaterialEntity material = materialDAO.selectById(id);
        if (material == null) return ResultUtil.error(-1, "网络错误");
        if (material.getIsDelete().equals(0)) {
            return ResultUtil.error(-1, "启用状态");
        }
        material.setIsDelete(0);
        materialDAO.updateById(material);
        return ResultUtil.success("审核成功");
    }

    public Result<String> noPassMaterial(Long id) {
        MaterialEntity material = materialDAO.selectById(id);
        if (material == null) return ResultUtil.error(-1, "网络错误");
        if (material.getIsDelete().equals(0)) {
            return ResultUtil.error(-1, "驳回状态");
        }
        material.setIsDelete(3);
        materialDAO.updateById(material);
        return ResultUtil.success("审核驳回");
    }

    public Result<String> passMaterialList(List<Long> idList) {
        for (Long id : idList) {
            MaterialEntity material = materialDAO.selectById(id);
            if (material == null) return ResultUtil.error(-1, "网络错误");
            material.setIsDelete(0);
            materialDAO.updateById(material);
        }
        return ResultUtil.success("审核成功");
    }

    public Result<String> noPassMaterialList(List<Long> idList) {
        for (Long id : idList) {
            MaterialEntity material = materialDAO.selectById(id);
            if (material == null) return ResultUtil.error(-1, "网络错误");
            material.setIsDelete(3);
            materialDAO.updateById(material);
        }
        return ResultUtil.success("驳回成功");
    }
}
