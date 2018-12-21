package com.cn.xt.mp.feedback.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.feedback.mpModel.TbFeedbackRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TbFeedbackRecordMapper  extends BasicMapper {
    TbFeedbackRecord selectByPrimaryKey(String id);
}