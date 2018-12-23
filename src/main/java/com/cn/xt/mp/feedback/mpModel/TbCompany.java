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

    private String awaitCodeImg;

    private String awaitCompanyImg;

    private String country;

    private String province;

    private String city;

    private String type;

    private Integer auth;//0待审核，1审核，2重新编辑待审核

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public String getAwaitCodeImg() {
        return awaitCodeImg;
    }

    public void setAwaitCodeImg(String awaitCodeImg) {
        this.awaitCodeImg = awaitCodeImg;
    }

    public String getAwaitCompanyImg() {
        return awaitCompanyImg;
    }

    public void setAwaitCompanyImg(String awaitCompanyImg) {
        this.awaitCompanyImg = awaitCompanyImg;
    }

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
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