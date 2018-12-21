package com.cn.xt.mp.feedback.wxSecurity.wxSecurity;

import com.alibaba.fastjson.JSONObject;
import com.cn.xt.mp.base.entity.Msg;
import com.cn.xt.mp.base.interfaces.AuthIgnore;
import com.cn.xt.mp.base.mybatis.model.BasicExample;
import com.cn.xt.mp.base.util.MD5Util;
import com.cn.xt.mp.feedback.mpModel.TbSysAccount;
import com.cn.xt.mp.feedback.mpModel.WxSecurityPO;
import com.cn.xt.mp.feedback.service.IWxSecurityService;
import com.cn.xt.mp.feedback.wxSecurity.util.WXUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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

    @RequestMapping("createMenu/{diy}")
    public Msg createMenu(@PathVariable String diy, HttpServletRequest request, HttpServletResponse response) throws Exception {
        WxSecurityPO security = wxSecurityService.getWxSecurityByDoMain(diy);
        if(security == null){
            return Msg.fail("处理的公众号尚未接入");
        }
        String menu = request.getParameter("menu");
        if(StringUtils.isEmpty(menu)) {

             menu = JSONObject.toJSONString(WXUtil.initMenu(security.getAppId(),security.getDiyDomain()));
            System.out.println(menu);
        }



        JSONObject result = WXUtil.createMenu(security.getAppId(),menu);
        if(result!=null){
            return Msg.success(result.getString("errmsg")).setCode(result.getInteger("errcode"));
        }
        return Msg.fail();
    }


    @AuthIgnore
    @RequestMapping("login")
    public Msg login(String account,String psw,HttpServletRequest request) throws Exception {
        String psw0 = MD5Util.encrypt(psw);
        BasicExample<TbSysAccount> ex = new BasicExample(TbSysAccount.class);
        ex.createCriteria().andVarEqualTo("account",account).andVarEqualTo("psw",psw0);
        TbSysAccount sys = wxSecurityService.selectOneByExample(ex);
        if(sys == null ){
            return Msg.fail();
        }
        request.getSession().setAttribute("sys",sys);
        return Msg.success();
    }
}
