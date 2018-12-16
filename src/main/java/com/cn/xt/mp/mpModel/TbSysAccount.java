package com.cn.xt.mp.mpModel;

import com.cn.xt.mp.base.mybatis.model.IBaseDBPO;

public class TbSysAccount extends IBaseDBPO {

    private Integer id;
    private String account;
    private String psw;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  String _getTableName(){
        return "sys_account";
    }

    public String _getPKColumnName(){
        return "id";
    };

    public Object _getPKValue(){
        return id;
    };

    public void _setPKValue(Object var){
            id= Integer.valueOf(var.toString());
    };
}
