package com.cn.xt.mp.base.exception;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cn.xt.mp.base.entity.Msg;
import com.cn.xt.mp.base.util.IPUtils;
import com.cn.xt.mp.base.util.ObjectMapUtils;
import com.cn.xt.mp.base.util.SpringUtil;
import com.cn.xt.mp.mpModel.TbExceptionLog;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

@ResponseBody
@ControllerAdvice
@Component
public class GlobalExceptionHandler{

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Autowired
   private    IExceptionLogService exceptionLogService;

    @ExceptionHandler
    private Msg exceptionHandle(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
//        if(e.getMessage().equals("nullUser")){
//            return Msg.fail().setCode(-1).add("msg", e.getMessage());
//        }
//        if (e instanceof LoginException) {
//            return Msg.fail().add("msg", e.getMessage());
//        }
//        if (e instanceof NullPointerException) {
//            return Msg.fail().add("msg", e.getMessage());
//        }

        if (e instanceof DiyException) {
            return Msg.fail(e.getMessage());
        }
        try {
                LOGGER.info(">>>>>>系统异常，记录异常信息到数据库------start------");
                // 远程访问IP
                String ip = IPUtils.getRemortIP(request);
                String uri = request.getRequestURI();
                String param =JSON.toJSONString(request.getParameterMap());
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw, true));
                // 插入异常日志到数据库
                TbExceptionLog log = new TbExceptionLog();
                log.setIp(ip);
//              log.setClassName(className);
                log.setMethodName(uri);
                log.setParam(param);
                log.setExceptionType(e.getClass().getSimpleName());
                log.setExceptionMsg(sw.toString()); // 异常详细信息
                log.setIsView(1); // 默认未读,1:为查看、2：已查看
                log.setAddtime(new Date());
                exceptionLogService.insertExceptionLogSelective(log);
                LOGGER.info(">>>>>>系统异常，记录异常信息到数据库------end------");
        } catch (Exception ex) {
           ExceptionCenter.insertExceptionLog(ex,null,this.getClass().getSimpleName(),"server");
        }finally {
            if (e instanceof AuthException) {
                return Msg.fail().add("msg", e.getMessage());
            }
            return  Msg.fail().add("msg",StringUtils.isEmpty(e.getMessage())?"系统出现异常":e.getMessage());
        }
    }

}