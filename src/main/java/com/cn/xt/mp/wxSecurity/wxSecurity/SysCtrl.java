package com.cn.xt.mp.wxSecurity.wxSecurity;

import com.alibaba.fastjson.JSONObject;
import com.cn.xt.mp.base.entity.Msg;
import com.cn.xt.mp.base.interfaces.AuthIgnore;
import com.cn.xt.mp.mpModel.WxSecurityPO;
import com.cn.xt.mp.service.IWxSecurityService;
import com.cn.xt.mp.wxSecurity.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/12 19:58
 **/
@RestController
@RequestMapping("sys")
public class SysCtrl {

    @Autowired
    private IWxSecurityService wxSecurityService;

    @AuthIgnore
    @RequestMapping("createMenu/{diy}")
    public Msg createMenu(@PathVariable String diy, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WxSecurityPO security = wxSecurityService.getWxSecurityByDoMain(diy);
        if(security == null){
            return Msg.fail("处理的公众号尚未接入");
        }
        String menu = JSONObject.toJSONString(WXUtil.initMenu());
        JSONObject result = WXUtil.createMenu(security.getAppId(),menu);
        if(result!=null){
            return Msg.success(result.getString("errmsg")).setCode(result.getInteger("errcode"));
        }
        return Msg.fail();
    }
}
