package com.safe.safecampusbackend.service.impl;

import com.safe.safecampusbackend.dao.InfoDAO;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import com.safe.safecampusbackend.model.vo.InfoListVO;
import com.safe.safecampusbackend.model.vo.InfoVO;
import com.safe.safecampusbackend.service.InfoService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
@AllArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoDAO infoDAO;

    /**
     * 首页拿到资讯列表
     *
     * @return 资讯列表
     */
    public Result<List<InfoListVO>> getInfoList() {
        List<InfoEntity> infoEntityList = infoDAO.selectList(null);
        List<InfoListVO> infoListVOList = infoEntityList.stream()
                .map(infoEntity -> {
                    InfoListVO infoListVO = new InfoListVO();
                    BeanUtils.copyProperties(infoEntity, infoListVO);
                    // 使用 SimpleDateFormat 将日期格式化为年月日
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(infoEntity.getInfoDate());
                    infoListVO.setInfoDate(formattedDate);  // 转换为字符串形式的年月日
                    return infoListVO;
                })
                .toList();
        return ResultUtil.success(infoListVOList);
    }

    /**
     * 根据 id 拿到资讯内容
     *
     * @param id 资讯 id
     * @return 资讯内容
     */
    public Result<InfoVO> getInfoById(String id) {
        InfoEntity infoEntity = infoDAO.selectById(id);
        if (infoEntity != null) {
            InfoVO infoVO = new InfoVO();
            BeanUtils.copyProperties(infoEntity, infoVO);
            // 使用 SimpleDateFormat 将日期格式化为年月日
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDate = sdf.format(infoEntity.getInfoDate());
            infoVO.setInfoDate(formattedDate);  // 转换为字符串形式的年月日
            return ResultUtil.success(infoVO);
        } else {
            return ResultUtil.error(-1, "无此资讯");
        }
    }
}
