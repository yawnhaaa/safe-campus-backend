package com.safe.safecampusbackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safe.safecampusbackend.model.entity.QuestionContentEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionContentDAO extends BaseMapper<QuestionContentEntity> {
}
