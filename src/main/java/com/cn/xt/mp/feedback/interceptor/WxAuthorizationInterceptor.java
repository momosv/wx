package com.cn.xt.mp.feedback.interceptor;


import com.cn.xt.mp.base.exception.DiyException;
import com.cn.xt.mp.base.interfaces.AuthIgnore;
import com.cn.xt.mp.base.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component("wxAuthorizationInterceptor")
public class WxAuthorizationInterceptor extends HandlerInterceptorAdapter {

     final static Set<String> ALLOW_PATH= new HashSet<>(Arrays.asList("/webjars","/wxSecurity/access/","wxSecurity/validCode/"));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore annotation;
        if(handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(AuthIgnore.class);
            if(null == annotation){
                Class<?> clazz = ((HandlerMethod) handler).getMethod().getDeclaringClass();
                annotation = clazz.getAnnotation(AuthIgnore.class);
            }
            //如果有@AuthIgnore注解，则不验证token
            if(annotation != null){
                return true;
            }
        }else{
            return true;
        }

        for (String s : ALLOW_PATH) {
            if(request.getServletPath().contains(s)){
                //"不需拦截
                return true;
            }
        }
        //获取用户凭证
        String token = request.getHeader(Constants.COMPANY_USER_TOKEN);
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(Constants.COMPANY_USER_TOKEN);
            if(StringUtils.isEmpty(token)){
                Object obj = request.getSession().getAttribute(Constants.COMPANY_USER_TOKEN);
                if(null==obj){
                    throw new DiyException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
                }
            }
        }
        return true;
    }
}
