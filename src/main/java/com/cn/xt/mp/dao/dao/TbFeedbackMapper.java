package com.cn.xt.mp.dao.dao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbFeedback;
import org.springframework.stereotype.Repository;

@Repository
public interface TbFeedbackMapper  extends BasicMapper {
    TbFeedback selectByPrimaryKey(String id);
}