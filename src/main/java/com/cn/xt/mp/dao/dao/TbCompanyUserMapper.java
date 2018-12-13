package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbCompanyUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface TbCompanyUserMapper  extends BasicMapper {
    TbCompanyUser selectByPrimaryKey(String id);

    @Select( "SELECT * FROM Tb_Company_User WHERE openid=#{openid}" )
    TbCompanyUser getCompanyUserByOpenid(String openid);
}