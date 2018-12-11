package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbAgency;
import org.springframework.stereotype.Repository;

@Repository
public interface TbAgencyMapper  extends BasicMapper {
    TbAgency selectByPrimaryKey(String id);
}