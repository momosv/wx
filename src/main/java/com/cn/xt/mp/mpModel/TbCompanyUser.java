package com.cn.xt.mp.mpModel;

import com.cn.xt.mp.base.mybatis.model.IBaseDBPO;

public class TbCompanyUser  extends IBaseDBPO {
    private String id;

    private String openid;

    private String account;

    private String psw;

    private String name;

    private String companyId;

    private Integer auth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw == null ? null : psw.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public  String _getTableName(){
        return "tb_company_user";
    }

    public String _getPKColumnName(){
        return "id";
    }

    public Object _getPKValue(){
        return id;
    }

    public void _setPKValue(Object var){
        id =  var.toString();
    }

    @Override
    public String toString() {
        return "TbCompanyUser{" +
                "id='" + id + '\'' +
                ", openid='" + openid + '\'' +
                ", account='" + account + '\'' +
                ", psw='" + psw + '\'' +
                ", name='" + name + '\'' +
                ", companyId='" + companyId + '\'' +
                ", auth=" + auth +
                '}';
    }
}