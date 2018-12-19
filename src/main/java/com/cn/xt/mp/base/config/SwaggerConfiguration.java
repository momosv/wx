package com.cn.xt.mp.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("mp用户信息授权接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cn.xt.mp.wxSecurity.wxSecurity"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createMonitorRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("手机端政府和企业")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cn.xt.mp.mobile"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createActivitiRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("工作流引擎")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lishiots.dc.activiti.ctl"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket createBaseRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("kernel模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lishiots.dc.kernel.ctl"))
                .paths(PathSelectors.any())
                .build();
    }
    @Bean
    public Docket createComplaintRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("投诉管理")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lishiots.dc.complaint.ctl"))
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger RESTful APIs")
                .description("swagger RESTful APIs")
                .termsOfServiceUrl("42052d4b.nat123.net")
                .contact("momojy@vip.qq.com")
                .version("1.0")
                .build();
    }
}