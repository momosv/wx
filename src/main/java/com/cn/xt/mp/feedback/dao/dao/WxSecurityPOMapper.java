package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.WxSecurityPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxSecurityPOMapper  extends BasicMapper {
    WxSecurityPO selectByPrimaryKey(Integer id);
}