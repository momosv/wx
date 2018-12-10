package com.cn.xt.mp.wx.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.wx.model.WxSecurityPO;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/10 16:27
 **/
public interface IWxSecurityService extends BasicService {
    WxSecurityPO getWxSecurityByAppId(String appId) throws Exception;

    WxSecurityPO getWxSecurityByDoMain(String diyDomain) throws Exception;
}
