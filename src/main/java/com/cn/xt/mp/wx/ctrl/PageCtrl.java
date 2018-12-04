package com.cn.xt.mp.wx.ctrl;

import com.alibaba.fastjson.JSONObject;
import com.cn.xt.mp.wx.util.WXUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/30 11:29
 **/
@Controller
@RequestMapping("page")
public class PageCtrl {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @ResponseBody
    @RequestMapping("wx/valid")
    public String getUserInfoBySilently(String code) throws Exception {
      JSONObject tObject = WXUtil.getUserTokenByCode(code);

       String openId = tObject.getString("openid");
       String user_token = tObject.getString("access_token");
        //判断数据库是否有记录信息
        if(false){
            return tObject.toJSONString();
        }else{
            return  WXUtil.getUserByUserToken(user_token,openId).toJSONString();
        }
    }
}
