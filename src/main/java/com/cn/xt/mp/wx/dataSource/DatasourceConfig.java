package com.cn.xt.mp.wx.dataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration
public class DatasourceConfig {
	private Logger logger = LoggerFactory.getLogger(DatasourceConfig.class);
    @Bean(name="dataSource")
    @Primary //不要漏了这
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource(){
        DruidDataSource datasource = DruidDataSourceBuilder.create().build();
        return datasource;  
    }

    @Bean(name="circleDataSource")
    @ConfigurationProperties(prefix = "spring.circle-datasource")
    public DataSource circleDataSource(){
        DruidDataSource datasource = DruidDataSourceBuilder.create().build();
        return datasource;
    }
    /////////  下面是druid 监控访问的设置  /////////////////  
    @Bean  
    public ServletRegistrationBean druidServlet() {  
        ServletRegistrationBean reg = new ServletRegistrationBean();  
        reg.setServlet(new StatViewServlet());  
        reg.addUrlMappings("/druid/*");  //url 匹配  
      //  reg.addInitParameter("allow", "192.168.16.110,127.0.0.1"); // IP白名单 (没有配置或者为空，则允许所有访问)  
      //  reg.addInitParameter("deny", "192.168.16.111"); //IP黑名单 (存在共同时，deny优先于allow)  
        reg.addInitParameter("loginUsername", this.druidLoginName);//登录名  
        reg.addInitParameter("loginPassword", this.druidPassword);//登录密码  
        reg.addInitParameter("resetEnable", "false"); // 禁用HTML页面上的“Reset All”功能  
        return reg;  
    }  
  
    @Bean(name="druidWebStatFilter")  
    public FilterRegistrationBean filterRegistrationBean() {  
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();  
        filterRegistrationBean.setFilter(new WebStatFilter());  
        filterRegistrationBean.addUrlPatterns("/*");  
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*"); //忽略资源  
        filterRegistrationBean.addInitParameter("profileEnable", "true");  
        filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");  
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");  
        return filterRegistrationBean;  
    }



    @Value("${spring.datasource.druidLoginName}")
    private String druidLoginName;

    @Value("${spring.datasource.druidPassword}")
    private String druidPassword;
}  

