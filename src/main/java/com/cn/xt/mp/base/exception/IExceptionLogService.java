package com.cn.xt.mp.base.exception;

import com.cn.xt.mp.base.mybatis.service.BasicService;
import com.cn.xt.mp.feedback.mpModel.TbExceptionLog;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/13 11:05
 **/

public interface IExceptionLogService extends BasicService {
    void insertExceptionLogSelective(TbExceptionLog exceptionLog);
}
