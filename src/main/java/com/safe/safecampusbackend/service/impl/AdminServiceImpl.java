package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.*;
import com.safe.safecampusbackend.model.dto.QuestionContentDTO;
import com.safe.safecampusbackend.model.dto.QuestionDTO;
import com.safe.safecampusbackend.model.dto.UserUpdatePasswdDTO;
import com.safe.safecampusbackend.model.entity.*;
import com.safe.safecampusbackend.model.vo.QuestionListVO;
import com.safe.safecampusbackend.model.vo.UserListAdminVO;
import com.safe.safecampusbackend.service.AdminService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import com.safe.safecampusbackend.util.salt.SaltUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserDAO userDAO;
    private final InfoDAO infoDAO;
    private final MaterialDAO materialDAO;
    private final QuestionDAO questionDAO;
    private final QuestionContentDAO questionContentDAO;

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
        queryWrapper.eq("material_type", 0); // 添加material_type条件
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        return ResultUtil.success(materialEntityList);
    }

    public Result<List<MaterialEntity>> getVideoReviewList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("is_delete", 2, 3);
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

    public Result<List<QuestionListVO>> getQuestionList() {
        List<QuestionEntity> questionEntityList = questionDAO.selectList(null);
        List<QuestionListVO> questionListVOList = new ArrayList<>();
        for (QuestionEntity question : questionEntityList) {
            QueryWrapper<QuestionContentEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("question_id", question.getId());
            List<QuestionContentEntity> itemList = questionContentDAO.selectList(queryWrapper);
            QuestionListVO questionListVO = new QuestionListVO();
            BeanUtils.copyProperties(question, questionListVO);
            questionListVO.setQuestionContentEntityList(itemList);
            questionListVOList.add(questionListVO);
        }
        return ResultUtil.success(questionListVOList);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> deleteQuestion(Long id) {
        try {
            QueryWrapper<QuestionContentEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("question_id", id);
            List<QuestionContentEntity> questionContentEntityList = questionContentDAO.selectList(queryWrapper);
            for (QuestionContentEntity entity : questionContentEntityList) {
                questionContentDAO.deleteById(entity.getId());
            }
            questionDAO.deleteById(id);

            return ResultUtil.success("删除成功");
        } catch (Exception e) {
            return ResultUtil.error(-1, e.getMessage());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> updateQuestion(QuestionDTO questionDTO) {
        try {
            // 更新题目信息
            QuestionEntity questionEntity = new QuestionEntity();
            BeanUtils.copyProperties(questionDTO, questionEntity);
            questionDAO.updateById(questionEntity);

            // 更新选项信息
            List<QuestionContentDTO> questionContentDTOList = questionDTO.getQuestionContentDTOList();
            if (questionContentDTOList != null && !questionContentDTOList.isEmpty()) {
                for (QuestionContentDTO questionContentDTO : questionContentDTOList) {
                    QuestionContentEntity questionContentEntity = questionContentDAO.selectById(questionContentDTO.getId());
                    if (questionContentEntity != null) {
                        if (questionContentDTO.getContent() != null) {
                            questionContentEntity.setContent(questionContentDTO.getContent());
                        }
                        if (questionContentDTO.getIsTrue() != null) {
                            questionContentEntity.setIsTrue(questionContentDTO.getIsTrue());
                        }
                        questionContentDAO.updateById(questionContentEntity);
                    }
                }
            }

            return ResultUtil.success("更新成功");
        } catch (Exception e) {
            // Handle exception
            return ResultUtil.error(-1, "更新失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> newQuestion(QuestionDTO questionDTO) {
        try {
            // 创建题目实体对象并复制属性
            QuestionEntity questionEntity = new QuestionEntity();
            BeanUtils.copyProperties(questionDTO, questionEntity);

            // 保存题目信息
            questionDAO.insert(questionEntity);

            // 处理选项信息
            List<QuestionContentDTO> questionContentDTOList = questionDTO.getQuestionContentDTOList();
            if (questionContentDTOList != null && !questionContentDTOList.isEmpty()) {
                for (QuestionContentDTO questionContentDTO : questionContentDTOList) {
                    QuestionContentEntity questionContentEntity = new QuestionContentEntity();
                    BeanUtils.copyProperties(questionContentDTO, questionContentEntity);
                    questionContentEntity.setQuestionId(questionEntity.getId()); // 设置关联的题目ID
                    questionContentDAO.insert(questionContentEntity);
                }
            }

            return ResultUtil.success("新增成功");
        } catch (Exception e) {
            // Handle exception
            return ResultUtil.error(-1, "新增失败");
        }
    }

}
