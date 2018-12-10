package com.cn.xt.mp.wx.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.wx.model.WxSecurityPO;

public interface WxSecurityPOMapper extends BasicMapper {
    WxSecurityPO selectByPrimaryKey(Integer id);
}