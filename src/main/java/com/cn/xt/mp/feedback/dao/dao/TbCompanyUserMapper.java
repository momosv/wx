package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbCompanyUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TbCompanyUserMapper  extends BasicMapper {
    TbCompanyUser selectByPrimaryKey(String id);

    @Select( "SELECT * FROM Tb_Company_User WHERE openid=#{openid}" )
    TbCompanyUser getCompanyUserByOpenid(String openid);
}