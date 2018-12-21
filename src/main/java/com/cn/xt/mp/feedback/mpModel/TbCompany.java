package com.cn.xt.mp.feedback.mpModel;

import com.cn.xt.mp.base.mybatis.model.IBaseDBPO;

public class TbCompany  extends IBaseDBPO {
    private String id;

    private String name;

    private String address;

    private String linkman;

    private String phone;

    private String email;

    private String socialCreditCode;

    private String codeImg;

    private String companyImg;

    private String cpuntry;

    private String province;

    private String city;

    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSocialCreditCode() {
        return socialCreditCode;
    }

    public void setSocialCreditCode(String socialCreditCode) {
        this.socialCreditCode = socialCreditCode == null ? null : socialCreditCode.trim();
    }

    public String getCodeImg() {
        return codeImg;
    }

    public void setCodeImg(String codeImg) {
        this.codeImg = codeImg == null ? null : codeImg.trim();
    }

    public String getCompanyImg() {
        return companyImg;
    }

    public void setCompanyImg(String companyImg) {
        this.companyImg = companyImg == null ? null : companyImg.trim();
    }

    public String getCpuntry() {
        return cpuntry;
    }

    public void setCpuntry(String cpuntry) {
        this.cpuntry = cpuntry == null ? null : cpuntry.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public  String _getTableName(){
        return "tb_company";
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
}