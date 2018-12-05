package com.cn.xt.mp.base.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component  
public class SpringUtil implements ApplicationContextAware {  
  
    private static ApplicationContext applicationContext = null;  
// 非@import显式注入，@Component是必须的，且该类必须与main同包或子包  
    // 若非同包或子包，则需手动import 注入，有没有@Component都一样  
    // 可复制到Test同包测试  
  
    @Override  
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {  
        if(SpringUtil.applicationContext == null){  
            SpringUtil.applicationContext  = applicationContext;  
        }
    }

    //获取applicationContext  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    //通过name获取 Bean.  
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    public static HttpServletRequest getHttpServletRequest(){
        HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static HttpServletResponse getHttpServletResponse(){
        HttpServletResponse response= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }
  
    //通过class获取Bean.  
    public static <T> T getBean(Class<T> clazz){  
        return getApplicationContext().getBean(clazz);  
    }  
  
    //通过name,以及Clazz返回指定的Bean  
    public static <T> T getBean(String name,Class<T> clazz){  
        return getApplicationContext().getBean(name, clazz);  
    }  
  
}  