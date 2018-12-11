package com.cn.xt.mp.wxSecurity.util;

import com.cn.xt.mp.base.exception.DiyException;
import com.cn.xt.mp.base.interfaces.AuthIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/12/11 17:52
 **/
@Controller
public class TestUtil {

    @AuthIgnore
    @RequestMapping("ex")
    public static void diy() throws DiyException {

        throw new DiyException("默默这个笨蛋");
    }
}
