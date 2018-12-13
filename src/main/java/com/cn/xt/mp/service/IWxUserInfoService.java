package com.cn.xt.mp.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.mpModel.WxUserInfoPO;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 20:07
 **/
public interface IWxUserInfoService  extends BasicService {
    WxUserInfoPO getWxUserInfoByOpenid(String openid);
}
