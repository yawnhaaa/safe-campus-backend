package com.safe.safecampusbackend.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.safe.safecampusbackend.model.entity.InfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InfoDAO extends BaseMapper<InfoEntity> {
}
