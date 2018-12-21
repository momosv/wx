package com.cn.xt.mp.feedback.dao.readonlydao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbExceptionLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadonlyTbExceptionLogMapper extends BasicMapper {
    TbExceptionLog selectByPrimaryKey(Integer id);
}