package com.cn.xt.mp.base.exception;

import com.cn.xt.mp.base.util.SpringUtil;
import com.cn.xt.mp.mpModel.TbExceptionLog;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Parameter;
import java.util.Date;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/13 11:29
 **/
public class ExceptionCenter {


    private static   IExceptionLogService exceptionLogService = SpringUtil.getBean("exceptionLogService",IExceptionLogService.class);

    public static void insertExceptionLog( Exception e,String exString,String className,String ip,String param){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw, true));
        TbExceptionLog log = new TbExceptionLog();
        log.setIp(ip);
        log.setClassName(className);
        log.setMethodName(null);
        log.setExceptionType(e.getClass().getSimpleName());
        log.setExceptionMsg(null==exString?sw.toString():exString); // 异常详细信息
        log.setIsView(1); // 默认未读,1:为查看、2：已查看
        log.setAddtime(new Date());
        log.setParam(param);
        exceptionLogService.insertExceptionLogSelective(log);
    }

    public static void insertExceptionLog( Exception e){
        insertExceptionLog(e,null,null,null,null);
    }
    public static void insertExceptionLog(Exception e, String g, String param){
        insertExceptionLog(e,g,null,null,param);
    }
}
