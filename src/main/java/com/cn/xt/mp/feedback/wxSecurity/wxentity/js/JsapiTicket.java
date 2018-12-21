package com.cn.xt.mp.feedback.wxSecurity.wxentity.js;

import java.io.Serializable;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/17 10:26
 **/
public class JsapiTicket implements Serializable {
    private String ticket;
    private String expiresIn;
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    public String getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}

