package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.InfoUserDTO;
import com.safe.safecampusbackend.model.dto.InfoUserStatusDTO;
import com.safe.safecampusbackend.model.dto.IssueInfoDTO;
import com.safe.safecampusbackend.model.vo.InfoListVO;
import com.safe.safecampusbackend.model.vo.InfoUserStatusVO;
import com.safe.safecampusbackend.model.vo.InfoVO;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface InfoService {
    /**
     * 获取资讯列表
     *
     * @return 资讯列表
     */
    Result<List<InfoListVO>> getInfoList();

    /**
     * 获取资讯详情
     *
     * @param id 资讯id
     * @return 资讯详情
     */
    Result<InfoVO> getInfoById(String id);

    /**
     * 用户点赞、收藏资讯操作
     *
     * @param infoUserDTO 用户id、资讯id、操作类型：0喜欢、1收藏
     * @return 成功与失败，靠code区分
     */
    Result<String> handleInfo(InfoUserDTO infoUserDTO);

    /**
     * 获取用户点赞、收藏状态
     *
     * @param infoUserStatusDTO 用户id、资讯id
     * @return 点赞、收藏状态
     */
    Result<InfoUserStatusVO> getInfoUserStatus(InfoUserStatusDTO infoUserStatusDTO);

    /**
     * 发布资讯
     *
     * @param issueInfoDTO 发布资讯必要入参
     * @return 发布状态
     */
    Result<String> issueInfo(IssueInfoDTO issueInfoDTO);
}
