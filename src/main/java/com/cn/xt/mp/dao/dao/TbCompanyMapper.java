package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbCompany;
import org.springframework.stereotype.Repository;

@Repository
public interface TbCompanyMapper  extends BasicMapper {
    TbCompany selectByPrimaryKey(String id);
}