package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbAcceptingUnitUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbAcceptingUnitUserMapper  extends BasicMapper {
    TbAcceptingUnitUser selectByPrimaryKey(String id);
}