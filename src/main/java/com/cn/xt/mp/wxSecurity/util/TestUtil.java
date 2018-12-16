package com.cn.xt.mp.wxSecurity.util;

import com.cn.xt.mp.base.exception.DiyException;
import com.cn.xt.mp.base.interfaces.AuthIgnore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
    public  void diy() throws Exception {

        throw new Exception("默默这个笨蛋");
    }
    @ResponseBody
    @AuthIgnore
    @RequestMapping("ex2")
    public String diy2(HttpServletRequest request) throws Exception {
        return (String) request.getSession().getAttribute("momo");
    }

    @ResponseBody
    @AuthIgnore
    @RequestMapping("ex3")
    public String diy3(HttpServletRequest request) throws Exception {

        request.getSession().setAttribute("momo","momoya");
        return (String) request.getSession().getAttribute("momo");
    }
}
