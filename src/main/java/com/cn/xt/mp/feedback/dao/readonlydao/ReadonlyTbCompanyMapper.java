package com.cn.xt.mp.feedback.dao.readonlydao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbCompany;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReadonlyTbCompanyMapper extends BasicMapper {
    TbCompany selectByPrimaryKey(String id);

    @Select("select * from tb_company where social_credit_code = #{code}")
    TbCompany getCompanyByCode(@Param("code") String code);
}