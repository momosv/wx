package com.example.wx;

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

    public static void main(String[] args) {


        SpringApplication.run(WxApplication.class, args);
        String path = "E:/momoGit/wx/src/main/resources/szt.jpg";
        try {
            String mediaId = WXUtil.upload1(path,WXUtil.TOKEN,"image");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
       // main1(null);
    }
    public static void main1(String[] args) {
        try {
            AccessToken tk = WXUtil.getAccessToken();
            System.out.println(tk.getToken());
            System.out.println(tk.getExpiresIn());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
//16_W6KVvr_MidK6UCDMEKUQtk4m-DXIE0HB2qY5qsc4BnzEemJNKJWZGerIqdYKFbahTkJbOnat5FKs9a0Z0HgCBG2IGkIVTZFxBDQh_34Qb3UrpcsFzVVF6YDwGSRQ4upTMfDGFJn2mvA6Dd6bORLfAJAJMG