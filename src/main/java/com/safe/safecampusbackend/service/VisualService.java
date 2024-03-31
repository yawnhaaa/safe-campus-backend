package com.safe.safecampusbackend.service;

import com.safe.safecampusbackend.model.vo.VisualVO;
import com.safe.safecampusbackend.util.result.Result;

import java.util.List;

public interface VisualService {
    /**
     * 获取可视化数据
     *
     * @return 可数化数据列表
     */
    Result<List<VisualVO>> getVisualList();
}
