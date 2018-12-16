package com.cn.xt.mp.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    private WxAuthorizationInterceptor wxAuthorizationInterceptor;
    @Autowired
    private SysAuthorizationInterceptor sysAuthorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sysAuthorizationInterceptor).addPathPatterns("/sys/**")
        .excludePathPatterns(Arrays.asList("/**.js","/**.css"));

        registry.addInterceptor(authorizationInterceptor).addPathPatterns("/mgr/**")
        .excludePathPatterns(Arrays.asList("/**.js","/**.css"));

        registry.addInterceptor(wxAuthorizationInterceptor).addPathPatterns("/mp/**","/wxSecurity/**")
                .excludePathPatterns(Arrays.asList("/**.js","/**.css","/wxSecurity/company/validCode/**"));
    }

}
