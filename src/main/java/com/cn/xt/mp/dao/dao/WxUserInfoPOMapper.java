package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.WxUserInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
public interface WxUserInfoPOMapper  extends BasicMapper {
    WxUserInfoPO selectByPrimaryKey(Integer id);


}