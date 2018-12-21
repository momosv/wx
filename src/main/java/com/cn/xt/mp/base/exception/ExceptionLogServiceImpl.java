package com.cn.xt.mp.base.exception;

import com.cn.xt.mp.base.mybatis.service.impl.BasicServiceImpl;
import com.cn.xt.mp.feedback.dao.dao.TbExceptionLogMapper;
import com.cn.xt.mp.feedback.mpModel.TbExceptionLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/13 11:06
 **/
@Service("exceptionLogService")
public class ExceptionLogServiceImpl extends BasicServiceImpl implements IExceptionLogService {
    @Autowired
    TbExceptionLogMapper tbExceptionLogMapper;

    @Autowired
    private void setMapper(){
        this.setMapper(tbExceptionLogMapper);
    }
    public void insertExceptionLogSelective(TbExceptionLog exceptionLog) {
            this.insertOne(exceptionLog);
    }

}
