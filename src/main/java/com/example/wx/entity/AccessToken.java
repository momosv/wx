package com.example.wx.entity;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/28 16:27
 **/
public class AccessToken {
      private  Integer   expiresIn;
      private  String   token;

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
