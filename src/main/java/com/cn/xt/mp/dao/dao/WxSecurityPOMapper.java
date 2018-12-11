package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.WxSecurityPO;
import org.springframework.stereotype.Repository;

@Repository
public interface WxSecurityPOMapper  extends BasicMapper {
    WxSecurityPO selectByPrimaryKey(Integer id);
}