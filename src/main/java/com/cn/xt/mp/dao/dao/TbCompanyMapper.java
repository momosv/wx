package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbCompany;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
public interface TbCompanyMapper  extends BasicMapper {
    TbCompany selectByPrimaryKey(String id);
}