package com.cn.xt.mp.feedback.mpModel;

import com.cn.xt.mp.base.mybatis.model.IBaseDBPO;

public class TbAcceptingUnit extends IBaseDBPO {
    private String id;

    private String name;

    private String address;

    private String city;

    private String country;

    private String province;

    private String linkman;

    private String phone;

    private String img;

    private String agencyId;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId == null ? null : agencyId.trim();
    }

    public  String _getTableName(){
        return "tb_accepting_unit";
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