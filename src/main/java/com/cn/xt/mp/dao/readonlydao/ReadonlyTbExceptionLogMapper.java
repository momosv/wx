package com.cn.xt.mp.dao.readonlydao;

import com.cn.xt.mp.base.mybatis.dao.BasicMapper;
import com.cn.xt.mp.mpModel.TbExceptionLog;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("readonlyTbExceptionLogMapper")
public interface ReadonlyTbExceptionLogMapper extends BasicMapper {
    TbExceptionLog selectByPrimaryKey(Integer id);
}