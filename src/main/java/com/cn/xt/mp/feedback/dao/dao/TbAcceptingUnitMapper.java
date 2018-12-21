package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbAcceptingUnit;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbAcceptingUnitMapper extends BasicMapper {
    TbAcceptingUnit selectByPrimaryKey(String id);
}