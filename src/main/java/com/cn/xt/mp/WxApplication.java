package com.cn.xt.mp;

import com.cn.xt.mp.base.exception.DiyException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import java.io.IOException;

@EnableAsync
@EnableCaching //redis 普通缓存
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 7200)// redis 共享session
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
