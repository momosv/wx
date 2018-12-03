package com.example.wx;

import com.alibaba.fastjson.JSONObject;
import com.example.wx.entity.AccessToken;
import com.example.wx.util.WXUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


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
