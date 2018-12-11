package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbAcceptingUnitUser;
import org.springframework.stereotype.Repository;

@Repository
public interface TbAcceptingUnitUserMapper  extends BasicMapper {
    TbAcceptingUnitUser selectByPrimaryKey(String id);
}