package com.safe.safecampusbackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safe.safecampusbackend.model.entity.MaterialEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MaterialDAO extends BaseMapper<MaterialEntity> {
}
