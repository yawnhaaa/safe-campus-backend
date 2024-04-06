package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.PoliceDAO;
import com.safe.safecampusbackend.model.dto.PoliceDTO;
import com.safe.safecampusbackend.model.entity.PoliceEntity;
import com.safe.safecampusbackend.model.vo.PoliceAdminListVO;
import com.safe.safecampusbackend.model.vo.PoliceListVO;
import com.safe.safecampusbackend.model.vo.PoliceVO;
import com.safe.safecampusbackend.service.PoliceService;
import com.safe.safecampusbackend.util.Util;
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
            policeListVO.setPoint(Util.stringToDoubleArray(entity.getLongLa()));
            policeListVOList.add(policeListVO);
        }
        return ResultUtil.success(policeListVOList);
    }

    public Result<List<PoliceAdminListVO>> getAdminPoliceList() {
        List<PoliceEntity> policeEntityList = policeDAO.selectList(null);
        List<PoliceAdminListVO> policeAdminListVOList = new ArrayList<>();
        for (PoliceEntity entity : policeEntityList) {
            PoliceAdminListVO policeAdminListVO = new PoliceAdminListVO();
            BeanUtils.copyProperties(entity, policeAdminListVO);
            policeAdminListVO.setPoint(Util.stringToDoubleArray(entity.getLongLa()));
            policeAdminListVOList.add(policeAdminListVO);
        }
        return ResultUtil.success(policeAdminListVOList);
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

    public Result<String> deletePoliceById(Long id) {
        policeDAO.deleteById(id);
        return ResultUtil.success("删除成功");
    }

    public Result<String> newPolice(PoliceDTO policeDTO) {
        PoliceEntity entity = new PoliceEntity();
        BeanUtils.copyProperties(policeDTO, entity);
        entity.setLongLa(Util.doubleArrayToString(policeDTO.getLongitude(), policeDTO.getLatitude()));
        try {
            policeDAO.insert(entity);
            return ResultUtil.success("新增成功");
        } catch (Exception e) {
            return ResultUtil.error(-1, "网络错误");
        }
    }

    public Result<String> updatePolice(PoliceDTO policeDTO) {
        PoliceEntity entity = new PoliceEntity();
        BeanUtils.copyProperties(policeDTO, entity);
        entity.setLongLa(Util.doubleArrayToString(policeDTO.getLongitude(), policeDTO.getLatitude()));
        try {
            policeDAO.updateById(entity);
            return ResultUtil.success("更新成功");
        } catch (Exception e) {
            return ResultUtil.error(-1, "网络错误");
        }
    }
}
