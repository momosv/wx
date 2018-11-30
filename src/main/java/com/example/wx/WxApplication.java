package com.example.wx;

import com.alibaba.fastjson.JSONObject;
import com.example.wx.entity.AccessToken;
import com.example.wx.util.WXUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;


@SpringBootApplication
public class WxApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(WxApplication.class, args);

    }
}
