package com.safe.safecampusbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.safe.safecampusbackend.dao.CommentDAO;
import com.safe.safecampusbackend.dao.InfoDAO;
import com.safe.safecampusbackend.model.dto.CommentDTO;
import com.safe.safecampusbackend.model.entity.CommentEntity;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import com.safe.safecampusbackend.model.vo.CommentVO;
import com.safe.safecampusbackend.service.CommentService;
import com.safe.safecampusbackend.util.result.Result;
import com.safe.safecampusbackend.util.result.ResultUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDAO commentDAO;
    private final InfoDAO infoDAO;

    public Result<List<CommentVO>> getComment(Long infoId) {
        QueryWrapper<InfoEntity> infoEntityQueryWrapper = new QueryWrapper<>();
        infoEntityQueryWrapper.eq("id", infoId);
        InfoEntity infoEntity = infoDAO.selectOne(infoEntityQueryWrapper);
        if (infoEntity == null) {
            return ResultUtil.error(-1, "当前资讯不存在");
        }
        QueryWrapper<CommentEntity> commentEntityQueryWrapper = new QueryWrapper<>();
        commentEntityQueryWrapper.eq("info_id", infoId);
        List<CommentEntity> commentEntityList = commentDAO.selectList(commentEntityQueryWrapper);
        if (commentEntityList.isEmpty()) {
            return ResultUtil.success(null);
        } else {
            List<CommentVO> commentVOList = new ArrayList<>();
            for (CommentEntity commentEntity : commentEntityList) {
                CommentVO commentVO = new CommentVO();
                BeanUtils.copyProperties(commentEntity, commentVO);
                commentVOList.add(commentVO);
            }
            return ResultUtil.success(commentVOList);
        }
    }

    public Result<String> sendComment(CommentDTO commentDTO) {
        return ResultUtil.success("待会");
    }
}
