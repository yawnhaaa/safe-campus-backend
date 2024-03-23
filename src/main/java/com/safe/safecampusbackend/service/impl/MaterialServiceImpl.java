package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.MaterialDAO;
import com.safe.safecampusbackend.model.entity.MaterialEntity;
import com.safe.safecampusbackend.model.vo.MaterialListVO;
import com.safe.safecampusbackend.model.vo.MaterialVO;
import com.safe.safecampusbackend.service.MaterialService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialDAO materialDAO;

    public Result<List<MaterialListVO>> getImageList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("material_type", 0);
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        if (materialEntityList != null && !materialEntityList.isEmpty()) {
            List<MaterialListVO> materialListVOList = new ArrayList<>();
            for (MaterialEntity materialEntity : materialEntityList) {
                MaterialListVO materialListVO = new MaterialListVO();
                try {
                    BeanUtils.copyProperties(materialEntity, materialListVO);
                    materialListVOList.add(materialListVO);
                } catch (Exception e) {
                    // 复制属性发生异常
                    e.printStackTrace();
                }
            }
            return ResultUtil.success(materialListVOList);
        } else {
            return ResultUtil.success(new ArrayList<>());
        }
    }

    public Result<List<MaterialListVO>> getVideoList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("material_type", 1);
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        if (materialEntityList != null && !materialEntityList.isEmpty()) {
            List<MaterialListVO> materialListVOList = new ArrayList<>();
            for (MaterialEntity materialEntity : materialEntityList) {
                MaterialListVO materialListVO = new MaterialListVO();
                try {
                    BeanUtils.copyProperties(materialEntity, materialListVO);
                    materialListVOList.add(materialListVO);
                } catch (Exception e) {
                    // 复制属性发生异常
                    e.printStackTrace();
                }
            }
            return ResultUtil.success(materialListVOList);
        } else {
            return ResultUtil.success(new ArrayList<>());
        }
    }

    public Result<List<MaterialListVO>> getAudioList() {
        QueryWrapper<MaterialEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("material_type", 2);
        List<MaterialEntity> materialEntityList = materialDAO.selectList(queryWrapper);
        if (materialEntityList != null && !materialEntityList.isEmpty()) {
            List<MaterialListVO> materialListVOList = new ArrayList<>();
            for (MaterialEntity materialEntity : materialEntityList) {
                MaterialListVO materialListVO = new MaterialListVO();
                try {
                    BeanUtils.copyProperties(materialEntity, materialListVO);
                    materialListVOList.add(materialListVO);
                } catch (Exception e) {
                    // 复制属性发生异常
                    e.printStackTrace();
                }
            }
            return ResultUtil.success(materialListVOList);
        } else {
            return ResultUtil.success(new ArrayList<>());
        }
    }

    public Result<MaterialVO> getMaterial(Long id) {
        MaterialEntity entity = materialDAO.selectById(id);
        if (entity != null) {
            MaterialVO materialVO = new MaterialVO();
            BeanUtils.copyProperties(entity, materialVO);
            return ResultUtil.success(materialVO);
        }
        return ResultUtil.error(-1, "网络错误");
    }

    public Result<String> downloadMaterial(Long id) {
        MaterialEntity entity = materialDAO.selectById(id);
        if (entity == null) {
            return ResultUtil.error(-1, "网络错误");
        }
        int num = entity.getDownload();
        entity.setDownload(++num);
        try {
            materialDAO.updateById(entity);
            return ResultUtil.success("下载成功");
        } catch (Exception e) {
            return ResultUtil.error(-1, "下载失败");
        }
    }
}
