package com.cn.xt.mp;

import com.cn.xt.mp.base.exception.DiyException;
import com.cn.xt.mp.wxSecurity.util.TestUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.io.IOException;


@EnableRedisHttpSession // redis 共享session
@SpringBootApplication
public class WxApplication  extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WxApplication.class);
    }


    public static void main(String[] args) throws IOException, DiyException {
      SpringApplication.run(WxApplication.class, args);
    }
}
