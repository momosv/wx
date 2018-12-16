package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbFeedbackRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
public interface TbFeedbackRecordMapper  extends BasicMapper {
    TbFeedbackRecord selectByPrimaryKey(String id);
}