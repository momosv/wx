package com.cn.xt.mp.wx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("ws")
public class WSPageController {

   @RequestMapping("/")
    public String index(){
       return "ws/html/index";
   }

}
