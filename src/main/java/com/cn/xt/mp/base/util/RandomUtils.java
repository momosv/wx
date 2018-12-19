package com.cn.xt.mp.base.util;

import java.text.DecimalFormat;
import java.util.UUID;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/18 21:17
 **/
public class RandomUtils {

    public static String getDateWithRan(){
       return XDateUtils.nowToFullString()+new DecimalFormat("000").format((int)(Math.random()*100));
    }

    public static String getUUID32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
