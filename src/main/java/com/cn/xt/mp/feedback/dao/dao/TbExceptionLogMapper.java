package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbExceptionLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbExceptionLogMapper extends BasicMapper {
    TbExceptionLog selectByPrimaryKey(Integer id);
}