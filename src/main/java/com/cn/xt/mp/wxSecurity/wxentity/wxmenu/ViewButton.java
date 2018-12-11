package com.cn.xt.mp.wxSecurity.wxentity.wxmenu;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/29 10:36
 **/
public class ViewButton extends  Button {
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ViewButton{" +
                "url='" + url + '\'' +
                '}';
    }
}
