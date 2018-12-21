package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.MessageTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MessageTemplateMapper extends BasicMapper {
    MessageTemplate selectByPrimaryKey(Integer id);

    @Select("select * from message_template where app_id=#{appId} and message_type=#{messageType}")
    MessageTemplate getMessageTemplateByType(@Param("appId") String appId, @Param("messageType") Integer messageType);
}