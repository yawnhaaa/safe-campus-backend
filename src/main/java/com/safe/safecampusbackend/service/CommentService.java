package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.dto.CommentDTO;
import com.safe.safecampusbackend.model.vo.CommentVO;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface CommentService {
    /**
     * 获取当前资讯评论数组
     *
     * @param infoId 当前资讯id
     * @return 评论数组
     */
    Result<List<CommentVO>> getComment(Long infoId);

    /**
     * 发送评论
     *
     * @param commentDTO 发送评论必要入参
     * @return 发送结果
     */
    Result<String> sendComment(CommentDTO commentDTO);
}
