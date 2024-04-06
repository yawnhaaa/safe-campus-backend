package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.PoliceDAO;
import com.safe.safecampusbackend.model.dto.PoliceDTO;
import com.safe.safecampusbackend.model.entity.PoliceEntity;
import com.safe.safecampusbackend.model.vo.PoliceListVO;
import com.safe.safecampusbackend.model.vo.PoliceVO;
import com.safe.safecampusbackend.service.PoliceService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PoliceServiceImpl implements PoliceService {
    private final PoliceDAO policeDAO;

    public Result<PoliceVO> getPoliceById(Long id) {
        PoliceVO policeVO = new PoliceVO();
        PoliceEntity entity = policeDAO.selectById(id);
        if (entity == null || !entity.getIsDelete().equals(0)) {
            return ResultUtil.error(-1, "网络错误");
        } else {
            BeanUtils.copyProperties(entity, policeVO);
            return ResultUtil.success(policeVO);
        }
    }

    public Result<List<PoliceListVO>> getPoliceList() {
        QueryWrapper<PoliceEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete", 0);
        List<PoliceEntity> policeEntityList = policeDAO.selectList(queryWrapper);
        List<PoliceListVO> policeListVOList = new ArrayList<>();
        for (PoliceEntity entity : policeEntityList) {
            PoliceListVO policeListVO = new PoliceListVO();
            BeanUtils.copyProperties(entity, policeListVO);
            policeListVOList.add(policeListVO);
        }
        return ResultUtil.success(policeListVOList);
    }

    public Result<List<PoliceEntity>> getAdminPoliceList() {
        return ResultUtil.success(policeDAO.selectList(null));
    }

    public Result<String> banPoliceById(Long id) {
        PoliceEntity entity = policeDAO.selectById(id);
        if (entity == null) {
            return ResultUtil.error(-1, "网络错误");
        }
        entity.setIsDelete(1);
        try {
            policeDAO.updateById(entity);
            return ResultUtil.success("禁用成功");
        } catch (Exception e) {
            return ResultUtil.error(-1, "网络错误");
        }
    }

    public Result<String> noBanPoliceById(Long id) {
        PoliceEntity entity = policeDAO.selectById(id);
        if (entity == null) {
            return ResultUtil.error(-1, "网络错误");
        }
        entity.setIsDelete(0);
        try {
            policeDAO.updateById(entity);
            return ResultUtil.success("启用成功");
        } catch (Exception e) {
            return ResultUtil.error(-1, "网络错误");
        }
    }

    public Result<String> newPolice(PoliceDTO policeDTO) {
        return ResultUtil.success("666");
    }

    public Result<String> updatePolice(PoliceDTO policeDTO) {
        return ResultUtil.success("666");
    }
}
