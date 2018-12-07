package com.cn.xt.mp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;


@SpringBootApplication
public class WxApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WxApplication.class);
    }


    public static void main(String[] args) throws IOException {
      SpringApplication.run(WxApplication.class, args);
    }
}
