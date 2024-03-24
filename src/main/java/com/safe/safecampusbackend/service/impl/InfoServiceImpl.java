package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.InfoDAO;
import com.safe.safecampusbackend.dao.InfoUserDAO;
import com.safe.safecampusbackend.dao.UserDAO;
import com.safe.safecampusbackend.model.dto.InfoUserDTO;
import com.safe.safecampusbackend.model.dto.InfoUserStatusDTO;
import com.safe.safecampusbackend.model.dto.IssueInfoDTO;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import com.safe.safecampusbackend.model.entity.InfoUserEntity;
import com.safe.safecampusbackend.model.entity.UserEntity;
import com.safe.safecampusbackend.model.vo.InfoListVO;
import com.safe.safecampusbackend.model.vo.InfoUserStatusVO;
import com.safe.safecampusbackend.model.vo.InfoVO;
import com.safe.safecampusbackend.service.InfoService;
import com.safe.safecampusbackend.util.Util;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class InfoServiceImpl implements InfoService {
    private final InfoDAO infoDAO;
    private final UserDAO userDao;
    private final InfoUserDAO infoUserDAO;
    private static final Logger logger = LogManager.getLogger(InfoServiceImpl.class);

    /**
     * 首页拿到资讯列表
     *
     * @return 资讯列表
     */
    public Result<List<InfoListVO>> getInfoList() {
        List<InfoEntity> infoEntityList = infoDAO.selectList(null);
        List<InfoListVO> infoListVOList = infoEntityList.stream()
                .filter(infoEntity -> infoEntity.getIsDelete() == 0)
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
            if (infoEntity.getIsDelete() != 0) {
                return ResultUtil.error(-1, "无此资讯");
            }
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

    /**
     * 用户点赞、收藏资讯操作
     *
     * @param infoUserDTO 用户昵称、资讯id、操作类型：0喜欢、1收藏
     * @return 成功与失败，靠code区分
     */
    public Result<String> handleInfo(InfoUserDTO infoUserDTO) {
        // 检查用户是否存在
        QueryWrapper<UserEntity> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", infoUserDTO.getUserName());
        UserEntity user = userDao.selectOne(userQueryWrapper);
        if (user == null) {
            return ResultUtil.error(-1, "该用户就不存在，怎么调的接口！");
        }
        // 检查资讯是否存在
        QueryWrapper<InfoEntity> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("id", infoUserDTO.getInfoId());
        InfoEntity info = infoDAO.selectOne(infoQueryWrapper);
        if (info == null) {
            return ResultUtil.error(-1, "该资讯就不存在，怎么调的接口！");
        }
        // 生成复合id
        String compoundId = Util.compoundId(infoUserDTO.getUserName(), infoUserDTO.getInfoId().toString());
        InfoUserEntity entity = new InfoUserEntity();
        entity.setUserName(infoUserDTO.getUserName());
        entity.setInfoId(infoUserDTO.getInfoId());
        entity.setInfoUserId(compoundId);
        // 获取当前时间
        Date currentTime = new Date();
        if (infoUserDTO.getType() == 0) {
            entity.setIsLike(infoUserDTO.getIsStatus() ? 1 : 0);
            entity.setLikeTime(currentTime);
        } else {
            entity.setIsCollect(infoUserDTO.getIsStatus() ? 1 : 0);
            entity.setCollectTime(currentTime);
        }
        // 检查复合id是否存在
        QueryWrapper<InfoUserEntity> infoUserQueryWrapper = new QueryWrapper<>();
        infoUserQueryWrapper.eq("info_user_id", compoundId);
        InfoUserEntity infoUserEntity = infoUserDAO.selectOne(infoUserQueryWrapper);
        if (infoUserEntity == null) {
            try {
                infoUserDAO.insert(entity);
                return ResultUtil.success("成功");
            } catch (Exception e) {
                return ResultUtil.error(-1, "网络错误");
            }
        } else {
            try {
                // 复合 id 存在，执行更新操作
                entity.setId(infoUserEntity.getId()); // 设置更新条目的主键
                infoUserDAO.updateById(entity);
                return ResultUtil.success("成功");
            } catch (Exception e) {
                return ResultUtil.error(-1, "网络错误");
            }
        }
    }

    /**
     * 获取用户点赞、收藏状态
     *
     * @param infoUserStatusDTO 用户昵称、资讯id
     * @return 点赞、收藏状态
     */
    public Result<InfoUserStatusVO> getInfoUserStatus(InfoUserStatusDTO infoUserStatusDTO) {
        // 检查用户是否存在
        QueryWrapper<UserEntity> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("name", infoUserStatusDTO.getUserName());
        UserEntity user = userDao.selectOne(userQueryWrapper);
        if (user == null) {
            return ResultUtil.error(-1, "该用户就不存在，怎么调的接口！");
        }
        // 检查资讯是否存在
        QueryWrapper<InfoEntity> infoQueryWrapper = new QueryWrapper<>();
        infoQueryWrapper.eq("id", infoUserStatusDTO.getInfoId());
        InfoEntity info = infoDAO.selectOne(infoQueryWrapper);
        if (info == null) {
            return ResultUtil.error(-1, "该资讯就不存在，怎么调的接口！");
        }
        // 生成复合id
        String compoundId = Util.compoundId(infoUserStatusDTO.getUserName(), infoUserStatusDTO.getInfoId().toString());
        // 检查复合id是否存在
        QueryWrapper<InfoUserEntity> infoUserQueryWrapper = new QueryWrapper<>();
        infoUserQueryWrapper.eq("info_user_id", compoundId);
        InfoUserEntity infoUserEntity = infoUserDAO.selectOne(infoUserQueryWrapper);
        InfoUserStatusVO entity = new InfoUserStatusVO();
        if (infoUserEntity == null) {
            entity.setLikeStatus(false);
            entity.setLikeStatus(false);
        } else {
            entity.setLikeStatus(infoUserEntity.getIsLike().equals(1));
            entity.setCollectStatus(infoUserEntity.getIsCollect().equals(1));
        }
        return ResultUtil.success(entity);
    }

    public Result<String> issueInfo(IssueInfoDTO issueInfoDTO) {
        if (issueInfoDTO.getTitle() == null) {
            return ResultUtil.error(-1, "标题不能为空");
        }
        if (issueInfoDTO.getAuthor() == null) {
            return ResultUtil.error(-1, "作者昵称不能为空");
        }
        if (issueInfoDTO.getAuthorId() == null) {
            return ResultUtil.error(-1, "作者id不能为空");
        }
        if (issueInfoDTO.getContent() == null) {
            return ResultUtil.error(-1, "资讯内容不能为空");
        }
        InfoEntity entity = new InfoEntity();
        BeanUtils.copyProperties(issueInfoDTO, entity);
        if (issueInfoDTO.getFile() != null) {
            try {
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
                String timestamp = now.format(formatter);
                // 获取上传的文件名
                String fileName = timestamp + '_' + issueInfoDTO.getFile().getOriginalFilename();
                // 构建目标文件对象
                File destFile = new File("/Users/ahao/project/resource/imgSrc/" + fileName);
                // 将上传的文件保存到目标文件中
                issueInfoDTO.getFile().transferTo(destFile);
                entity.setImg(fileName);
            } catch (IOException e) {
                logger.error(e);
            }
        }

        Date currentTime = new Date();
        entity.setIsDelete(1);
        entity.setInfoDate(currentTime);
        try {
            infoDAO.insert(entity);
            return ResultUtil.success("已发布,等待审核");
        } catch (Exception e) {
            return ResultUtil.error(-1, "网络错误");
        }
    }
}
