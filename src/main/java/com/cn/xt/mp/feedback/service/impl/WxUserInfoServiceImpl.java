package com.cn.xt.mp.feedback.service.impl;

import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;
import com.cn.xt.mp.feedback.dao.dao.WxUserInfoPOMapper;
import com.cn.xt.mp.feedback.dao.readonlydao.ReadonlyWxUserInfoPOMapper;
import com.cn.xt.mp.feedback.mpModel.WxUserInfoPO;
import com.cn.xt.mp.feedback.service.IWxUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 20:07
 **/
@Service("wxUserInfoService")
public class WxUserInfoServiceImpl extends BasicServiceImpl implements IWxUserInfoService {

    @Autowired
    WxUserInfoPOMapper wxUserInfoPOMapper;
    @Autowired
    ReadonlyWxUserInfoPOMapper readonlyWxUserInfoPOMapper;
    @Autowired
    public void setWxUserInfoPOMapper(){
        this.setMapper(wxUserInfoPOMapper);
    }

    @Override
    public WxUserInfoPO getWxUserInfoByOpenid(String openid){
        return readonlyWxUserInfoPOMapper.getWxUserInfoByOpenid(openid);
    }

}
