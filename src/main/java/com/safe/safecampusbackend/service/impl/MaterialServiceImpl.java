package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.MaterialDAO;
import com.safe.safecampusbackend.model.dto.IssueMaterialDTO;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import com.safe.safecampusbackend.model.entity.MaterialEntity;
import com.safe.safecampusbackend.model.vo.MaterialListVO;
import com.safe.safecampusbackend.model.vo.MaterialVO;
import com.safe.safecampusbackend.service.MaterialService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
                if (materialEntity.getIsDelete() == 0) { // 根据 is_delete 字段进行判断
                    MaterialListVO materialListVO = new MaterialListVO();
                    try {
                        BeanUtils.copyProperties(materialEntity, materialListVO);
                        materialListVOList.add(materialListVO);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
                if (materialEntity.getIsDelete() == 0) { // 根据 is_delete 字段进行判断
                    MaterialListVO materialListVO = new MaterialListVO();
                    try {
                        BeanUtils.copyProperties(materialEntity, materialListVO);
                        materialListVOList.add(materialListVO);
                    } catch (Exception e) {
                        // 复制属性发生异常
                        e.printStackTrace();
                    }
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
                if (materialEntity.getIsDelete() == 0) { // 根据 is_delete 字段进行判断
                    MaterialListVO materialListVO = new MaterialListVO();
                    try {
                        BeanUtils.copyProperties(materialEntity, materialListVO);
                        materialListVOList.add(materialListVO);
                    } catch (Exception e) {
                        // 复制属性发生异常
                        e.printStackTrace();
                    }
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
            if (entity.getIsDelete() != 0) {
                return ResultUtil.error(-1, "无此素材");
            }
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
        if (entity.getIsDelete() != 0) {
            return ResultUtil.error(-1, "无此素材");
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

    public Result<String> issueMaterial(IssueMaterialDTO issueMaterialDTO) {
        if (issueMaterialDTO.getTitle() == null) {
            return ResultUtil.error(-1, "标题不能为空");
        }
        if (issueMaterialDTO.getAuthor() == null) {
            return ResultUtil.error(-1, "网络错误");
        }
        if (issueMaterialDTO.getAuthorId() == null) {
            return ResultUtil.error(-1, "网络错误");
        }
        if (issueMaterialDTO.getMaterialType() == null) {
            return ResultUtil.error(-1, "素材类型不能为空");
        }
        if (issueMaterialDTO.getFile() == null) {
            return ResultUtil.error(-1, "素材不能为空");
        }
        MaterialEntity entity = new MaterialEntity();
        BeanUtils.copyProperties(issueMaterialDTO, entity);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String timestamp = now.format(formatter);
        try {
            // 获取上传的文件名
            String fileName = timestamp + '_' + issueMaterialDTO.getFile().getOriginalFilename();
            // 构建目标文件对象
            Map<Integer, String> materialTypeMap = Map.of(
                    0, "images/",
                    1, "videos/",
                    2, "audios/"
            );
            String dirPath = materialTypeMap.getOrDefault(issueMaterialDTO.getMaterialType(), "others/");
            File destFile = new File("/Users/ahao/project/resource/" + dirPath + fileName);
            // 将上传的文件保存到目标文件中
            issueMaterialDTO.getFile().transferTo(destFile);
            entity.setSrc(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (issueMaterialDTO.getImgFile() != null) {
            try {
                // 获取上传的文件名
                String fileName = timestamp + '_' + issueMaterialDTO.getImgFile().getOriginalFilename();
                // 构建目标文件对象
                File destFile = new File("/Users/ahao/project/resource/imgSrc/" + fileName);
                // 将上传的文件保存到目标文件中
                issueMaterialDTO.getImgFile().transferTo(destFile);
                entity.setImgSrc(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Date currentTime = new Date();
        entity.setIsDelete(1);
        entity.setMaterialDate(currentTime);
        try {
            materialDAO.insert(entity);
            return ResultUtil.success("已发布,等待审核");
        } catch (Exception e) {
            return ResultUtil.error(-1, "网络错误");
        }
    }
}
