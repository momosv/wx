package com.cn.xt.mp.feedback.service.impl;

import com.cn.xt.mp.base.mybatis.model.BasicExample;
import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;

import com.cn.xt.mp.feedback.dao.dao.WxSecurityPOMapper;
import com.cn.xt.mp.feedback.dao.readonlydao.ReadonlyWxSecurityPOMapper;
import com.cn.xt.mp.feedback.mpModel.WxSecurityPO;
import com.cn.xt.mp.feedback.service.IWxSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    ReadonlyWxSecurityPOMapper readonlyWxSecurityPOMapper;
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
    @Cacheable(cacheNames="getWxSecurityByAppId",key="#appId")
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
    @Cacheable(cacheNames="getWxSecurityByDoMain",key="#doMain")
    public WxSecurityPO getWxSecurityByDoMain(String doMain) throws Exception {
        BasicExample example = new BasicExample(WxSecurityPO.class);
        example.createCriteria().andVarEqualTo("diy_domain",doMain);
         WxSecurityPO po =(WxSecurityPO)this.selectOneByExample(example);
        return po;

    }

}
