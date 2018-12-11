package com.cn.xt.mp.wxSecurity.wxentity.wxmenu;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/29 10:36
 **/
public class ClickButton extends  Button {
    private String key;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ClickButton{" +
                "key='" + key + '\'' +
                '}';
    }
}
