package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbCompanyUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbCompanyUserMapper  extends BasicMapper {
    TbCompanyUser selectByPrimaryKey(String id);
}