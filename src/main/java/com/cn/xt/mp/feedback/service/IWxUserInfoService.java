package com.cn.xt.mp.feedback.service;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.feedback.mpModel.WxUserInfoPO;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 20:07
 **/
public interface IWxUserInfoService  extends BasicService {
    WxUserInfoPO getWxUserInfoByOpenid(String openid);
}
