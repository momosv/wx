package com.cn.xt.mp.wx.service.impl;

import com.cn.xt.mp.base.mybatis.model.BasicExample;
import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;
import com.cn.xt.mp.wx.dao.WxSecurityPOMapper;
import com.cn.xt.mp.wx.model.WxSecurityPO;
import com.cn.xt.mp.wx.service.IWxSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/10 16:28
 **/
@Service("wxSecurityService")
public class WxSecurityServiceImpl extends BasicServiceImpl implements IWxSecurityService {

    @Autowired
    WxSecurityPOMapper wxSecurityPOMapper;
    @Autowired
    public void setWxSecurityPOMapper(){
        this.setMapper(wxSecurityPOMapper);
    }

    /**
     * 根据appid
     * @param appId
     * @return
     * @throws Exception
     */
    @Override
    public WxSecurityPO getWxSecurityByAppId(String appId) throws Exception {
        BasicExample example = new BasicExample(WxSecurityPO.class);
        example.createCriteria().andVarEqualTo("app_id",appId);
        return (WxSecurityPO)this.selectOneByExample(example);
    }
    /**
     * 根据个性域名
     * @param doMain
     * @return
     * @throws Exception
     */
    @Override
    public WxSecurityPO getWxSecurityByDoMain(String doMain) throws Exception {
        BasicExample example = new BasicExample(WxSecurityPO.class);
        example.createCriteria().andVarEqualTo("diy_domain",doMain);
        return (WxSecurityPO)this.selectOneByExample(example);
    }

}
