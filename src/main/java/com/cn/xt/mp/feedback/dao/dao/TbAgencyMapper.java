package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbAgency;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbAgencyMapper  extends BasicMapper {
    TbAgency selectByPrimaryKey(String id);
}