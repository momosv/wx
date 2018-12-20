package com.cn.xt.mp.dao.readonlydao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbFeedback;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReadonlyTbFeedbackMapper extends BasicMapper {
    TbFeedback selectByPrimaryKey(String id);
}