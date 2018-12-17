package com.cn.xt.mp.wxSecurity.util;

import com.alibaba.fastjson.JSONObject;
import com.cn.xt.mp.base.redis.util.RedisUtils;
import com.cn.xt.mp.wxSecurity.wxentity.AccessToken;
import com.cn.xt.mp.wxSecurity.wxentity.js.JsapiTicket;

import java.util.concurrent.TimeUnit;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/17 10:27
 **/

public class JsapiUtil {

    public final static String JS_API_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

    //access_token httprequest方法不再说明
    public static JsapiTicket getJsapiTicket(String appid) throws Exception {
        if (RedisUtils.hasKey("wx::jsticket::" + appid)) {
            JsapiTicket ticket = (JsapiTicket) RedisUtils.get("wx::jsticket::" + appid);
            return ticket;
        }
        AccessToken token = WXUtil.getAccessToken(appid);

        JsapiTicket ticket=new JsapiTicket();
        String requestUrl = JS_API_TICKET_URL.replace("ACCESS_TOKEN",token.getToken());
        JSONObject jsonObject =WXUtil.doGetStr(requestUrl);
        if(jsonObject.getString("errcode").equals("0")){
            ticket.setTicket(jsonObject.getString("ticket"));
            ticket.setExpiresIn(jsonObject.getString("expires_in"));
        }else{
            throw new Exception("获取jsapiticket失败:appid="+appid);
        }
        RedisUtils.set("wx::jsticket::"+appid,ticket,7000,TimeUnit.SECONDS);
        return ticket;
    }


}
