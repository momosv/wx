package com.cn.xt.mp.wxSecurity.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author linshengwen
 * @version 1.0
 * @description
 * @date 2018/11/30 11:29
 **/
@Controller
@RequestMapping("page")
public class PageCtrl {

    @RequestMapping("index")
    public String index(){
        return "index";
    }


}
