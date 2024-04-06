package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.PoliceDTO;
import com.safe.safecampusbackend.model.entity.PoliceEntity;
import com.safe.safecampusbackend.model.vo.PoliceAdminListVO;
import com.safe.safecampusbackend.model.vo.PoliceListVO;
import com.safe.safecampusbackend.model.vo.PoliceVO;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface PoliceService {
    /**
     * 获得详细数据
     *
     * @param id id
     * @return 详细数据
     */
    Result<PoliceVO> getPoliceById(Long id);

    /**
     * 获得坐标系与列表数据
     *
     * @return 坐标系与列表数据
     */
    Result<List<PoliceListVO>> getPoliceList();

    /**
     * 获得所有数据
     *
     * @return 获得所有数据
     */
    Result<List<PoliceAdminListVO>> getAdminPoliceList();

    /**
     * 禁用该数据
     *
     * @param id id
     * @return 结果
     */
    Result<String> banPoliceById(Long id);

    /**
     * 启用该数据
     *
     * @param id id
     * @return 结果
     */

    Result<String> noBanPoliceById(Long id);

    /**
     * 新增数据
     *
     * @param policeDTO 数据必要字段
     * @return 结果
     */
    Result<String> newPolice(PoliceDTO policeDTO);

    /**
     * 更新数据
     *
     * @param policeDTO 数据必要字段
     * @return 结果
     */
    Result<String> updatePolice(PoliceDTO policeDTO);
}
