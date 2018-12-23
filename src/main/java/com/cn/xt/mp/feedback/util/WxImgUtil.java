package com.cn.xt.mp.feedback.util;

import com.cn.xt.mp.base.exception.ExceptionCenter;
import com.cn.xt.mp.base.util.Constants;
import com.cn.xt.mp.feedback.wxSecurity.service.TempMaterialService;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WxImgUtil {

    public static String saveImg(String[] serverId,String token,String diy){
        if(serverId == null || serverId.length<1)return "";
        String img = Arrays.stream(serverId).map(e -> {
            try {
                File file =TempMaterialService.getTempMaterial(token, e, Constants.UPLOAD_DIR_ROOT+"/"+diy);
                if(file!=null){
                      return file.getPath().toString().substring(16);
                }
                return "";
            } catch (Exception e1) {
                ExceptionCenter.insertExceptionLog(e1, "新增feedback时收集serverId(img)异常",e);
                return "";
            }
        }).collect(Collectors.joining(","));
        return img;
    }

    public  static String saveImg(String serverId,String token,String diy){
       return saveImg(serverId.split(","),token,diy);
    }
}
