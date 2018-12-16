package com.cn.xt.mp.dao.readonlydao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.WxSecurityPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface ReadonlyWxSecurityPOMapper extends BasicMapper {
    WxSecurityPO selectByPrimaryKey(Integer id);
}