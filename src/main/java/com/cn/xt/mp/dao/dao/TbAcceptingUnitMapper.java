package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbAcceptingUnit;
import org.springframework.stereotype.Repository;

@Repository
public interface TbAcceptingUnitMapper extends BasicMapper {
    TbAcceptingUnit selectByPrimaryKey(String id);
}