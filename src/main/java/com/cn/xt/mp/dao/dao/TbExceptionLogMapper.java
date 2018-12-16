package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbExceptionLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
public interface TbExceptionLogMapper extends BasicMapper {
    TbExceptionLog selectByPrimaryKey(Integer id);
}