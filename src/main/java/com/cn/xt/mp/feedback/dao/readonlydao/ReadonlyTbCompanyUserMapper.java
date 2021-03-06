package com.cn.xt.mp.feedback.dao.readonlydao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbCompanyUser;
import com.cn.xt.mp.feedback.vo.CompanyUserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReadonlyTbCompanyUserMapper extends BasicMapper {
    TbCompanyUser selectByPrimaryKey(String id);

    @Select( "select cu.id,cu.openid,wu.app_id,cu.account,cu.name,cu.auth,cu.company_id,cu.email,cu.phone,cu.address ," +
            " wu.nickname,wu.sex,wu.headimgurl,c.name as company_name" +
            " from  wx_user_info wu ,tb_company_user cu left join tb_company c on cu.company_id = c.id " +
            "where cu.openid=wu.openid and cu.openid=#{openid} " )
    CompanyUserVO getCompanyVOUserByOpenid(String openid);
}