package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.InfoDAO;
import com.safe.safecampusbackend.dao.MaterialDAO;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import com.safe.safecampusbackend.model.entity.MaterialEntity;
import com.safe.safecampusbackend.model.vo.VisualVO;
import com.safe.safecampusbackend.service.VisualService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class VisualServiceImpl implements VisualService {
    private final InfoDAO infoDAO;
    private final MaterialDAO materialDAO;

    public Result<List<VisualVO>> getVisualList() {
        QueryWrapper<InfoEntity> infoEntityQueryWrapper = new QueryWrapper<>();
        infoEntityQueryWrapper.eq("is_delete", 0);
        List<InfoEntity> infoEntityList = infoDAO.selectList(infoEntityQueryWrapper);
        List<VisualVO> visualVOList = new ArrayList<>();
        VisualVO infoVO = new VisualVO();
        infoVO.setTitle("本站反诈资讯数量");
        infoVO.setNumber(infoEntityList.size());
        visualVOList.add(infoVO);
        QueryWrapper<MaterialEntity> materialEntityQueryWrapper = new QueryWrapper<>();
        materialEntityQueryWrapper.eq("is_delete", 0);
        List<MaterialEntity> materialEntityList = materialDAO.selectList(materialEntityQueryWrapper);

        int imageCount = 0;
        int videoCount = 0;
        int audioCount = 0;

        for (MaterialEntity materialEntity : materialEntityList) {
            if (materialEntity.getMaterialType() == 0) {
                imageCount++;
            } else if (materialEntity.getMaterialType() == 1) {
                videoCount++;
            } else if (materialEntity.getMaterialType() == 2) {
                audioCount++;
            }
        }

        VisualVO imageVO = new VisualVO();
        imageVO.setTitle("本站图像素材数量");
        imageVO.setNumber(imageCount);
        visualVOList.add(imageVO);
        VisualVO videoVO = new VisualVO();
        videoVO.setTitle("本站视频素材数量");
        videoVO.setNumber(videoCount);
        visualVOList.add(videoVO);
        VisualVO audioVO = new VisualVO();
        audioVO.setTitle("本站音频素材数量");
        audioVO.setNumber(videoCount);
        visualVOList.add(audioVO);
        return ResultUtil.success(visualVOList);
    }
}
