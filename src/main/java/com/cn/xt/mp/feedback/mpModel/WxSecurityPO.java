package com.cn.xt.mp.feedback.mpModel;

import com.cn.xt.mp.base.mybatis.model.IBaseDBPO;

import java.io.Serializable;

public class WxSecurityPO  extends IBaseDBPO implements Serializable{
    private Integer id;

    private String appId;

    private String appSecret;

    private String serverUrl;

    private String token;

    private String diyDomain;

    private String agencyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl == null ? null : serverUrl.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getDiyDomain() {
        return diyDomain;
    }

    public void setDiyDomain(String diyDomain) {
        this.diyDomain = diyDomain == null ? null : diyDomain.trim();
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId == null ? null : agencyId.trim();
    }

    public  String _getTableName(){
        return "wx_security";
    }

    public String _getPKColumnName(){
        return "id";
    }

    public Object _getPKValue(){
        return id;
    }

    public void _setPKValue(Object var){
        id =  Integer.valueOf(var.toString());
    }
}