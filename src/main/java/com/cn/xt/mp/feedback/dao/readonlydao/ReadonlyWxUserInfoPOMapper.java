package com.cn.xt.mp.feedback.dao.readonlydao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.WxUserInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ReadonlyWxUserInfoPOMapper extends BasicMapper {
    WxUserInfoPO selectByPrimaryKey(Integer id);

    @Select( "SELECT * FROM wx_user_info WHERE openid=#{openid}" )
    WxUserInfoPO getWxUserInfoByOpenid(String openid);
}