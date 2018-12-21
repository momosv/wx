package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbCompany;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbCompanyMapper  extends BasicMapper {
    TbCompany selectByPrimaryKey(String id);
}