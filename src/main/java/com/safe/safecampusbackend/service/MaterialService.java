package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.IssueMaterialDTO;
import com.safe.safecampusbackend.model.vo.MaterialListVO;
import com.safe.safecampusbackend.model.vo.MaterialVO;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface MaterialService {
    /**
     * 获取图片素材数组
     *
     * @return 图片素材数组
     */
    Result<List<MaterialListVO>> getImageList();

    /**
     * 获取视频素材数组
     *
     * @return 视频素材数组
     */
    Result<List<MaterialListVO>> getVideoList();

    /**
     * 获取音频素材数组
     *
     * @return 音频素材数组
     */
    Result<List<MaterialListVO>> getAudioList();

    /**
     * 获取素材页
     *
     * @param id 素材id
     * @return 素材相关信息
     */
    Result<MaterialVO> getMaterial(Long id);

    /**
     * 下载增加下载次数
     *
     * @param id 素材id
     * @return 成功与否
     */
    Result<String> downloadMaterial(Long id);

    /**
     * 发布素材
     *
     * @param issueMaterialDTO 素材必要字段
     * @return 成功与否
     */
    Result<String> issueMaterial(IssueMaterialDTO issueMaterialDTO);
}
