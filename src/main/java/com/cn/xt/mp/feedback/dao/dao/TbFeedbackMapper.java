package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbFeedback;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbFeedbackMapper  extends BasicMapper {
    TbFeedback selectByPrimaryKey(String id);
}