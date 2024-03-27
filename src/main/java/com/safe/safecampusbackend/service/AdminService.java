package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.UserUpdatePasswdDTO;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import com.safe.safecampusbackend.model.entity.MaterialEntity;
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

    /**
     * 获取逻辑删除为0，1的InfoList
     *
     * @return InfoList
     */
    Result<List<InfoEntity>> getInfoList();

    /**
     * 启用资讯
     *
     * @param id 资讯id
     * @return 结果
     */
    Result<String> noBanInfo(Long id);

    /**
     * 禁用资讯
     *
     * @param id 资讯id
     * @return 结果
     */
    Result<String> banInfo(Long id);

    /**
     * 删除资讯
     *
     * @param id 资讯id
     * @return 结果
     */
    Result<String> deleteInfo(Long id);

    /**
     * 启用资讯列表
     *
     * @param idList 资讯id列表
     * @return 结果
     */
    Result<String> noBanInfoList(List<Long> idList);

    /**
     * 禁用资讯列表
     *
     * @param idList 资讯id列表
     * @return 结果
     */
    Result<String> banInfoList(List<Long> idList);

    /**
     * 删除资讯列表
     *
     * @param idList 资讯id列表
     * @return 结果
     */
    Result<String> deleteInfoList(List<Long> idList);

    /**
     * 获取资讯审核列表
     *
     * @return 资讯审核列表
     */
    Result<List<InfoEntity>> getInfoReviewList();

    /**
     * 审核资讯
     *
     * @param id 资讯id
     * @return 结果
     */
    Result<String> reviewInfo(Long id);

    /**
     * 审核资讯列表
     *
     * @param idList 资讯列表
     * @return 结果
     */
    Result<String> reviewInfoList(List<Long> idList);

    /**
     * 获取is_delete为0，1的列表
     *
     * @return is_delete为0，1的列表
     */
    Result<List<MaterialEntity>> getImageList();

    /**
     * 获取is_delete为0，1的列表
     *
     * @return is_delete为0，1的列表
     */
    Result<List<MaterialEntity>> getVideoList();

    /**
     * 获取is_delete为0，1的列表
     *
     * @return is_delete为0，1的列表
     */
    Result<List<MaterialEntity>> getAudioList();

    /**
     * 启用入参id的数据
     *
     * @param id 主键
     * @return 结果
     */
    Result<String> noBanMaterial(Long id);


    /**
     * 启用id列表的数据
     *
     * @param idList id列表
     * @return 结果
     */
    Result<String> noBanMaterialList(List<Long> idList);


    /**
     * 禁用入参id的数据
     *
     * @param id 主键
     * @return 结果
     */
    Result<String> banMaterial(Long id);

    /**
     * 禁用id列表的数据
     *
     * @param idList id列表
     * @return 结果
     */
    Result<String> banMaterialList(List<Long> idList);

    /**
     * 删除入参id的数据
     *
     * @param id 主键
     * @return 结果
     */
    Result<String> deleteMaterial(Long id);

    /**
     * 删除id列表的数据
     *
     * @param idList id列表
     * @return 结果
     */
    Result<String> deleteMaterialList(List<Long> idList);

    /**
     * 获取is_delete为2的列表
     *
     * @return is_delete为2的列表
     */
    Result<List<MaterialEntity>> getImageReviewList();

    /**
     * 获取is_delete为2的列表
     *
     * @return is_delete为2的列表
     */
    Result<List<MaterialEntity>> getVideoReviewList();

    /**
     * 获取is_delete为2的列表
     *
     * @return is_delete为2的列表
     */
    Result<List<MaterialEntity>> getAudioReviewList();

    /**
     * 审核入参id的数据
     *
     * @param id 主键
     * @return 结果
     */
    Result<String> reviewMaterial(Long id);

    /**
     * 审核id列表的数据
     *
     * @param idList id列表
     * @return 结果
     */
    Result<String> reviewMaterialList(List<Long> idList);
}
