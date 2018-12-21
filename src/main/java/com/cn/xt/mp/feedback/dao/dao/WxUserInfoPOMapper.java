package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.WxUserInfoPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxUserInfoPOMapper  extends BasicMapper {
    WxUserInfoPO selectByPrimaryKey(Integer id);


}