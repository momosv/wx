package com.cn.xt.mp.wx.controller;

import cn.momosv.blog.base.mybatis.model.base.BasicExample;
import cn.momosv.blog.base.mybatis.model.base.Msg;
import cn.momosv.blog.circle.service.UserService;
import com.cn.xt.mp.wx.TbUserPO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CircleController {

    @Autowired
    UserService userService;


    @ResponseBody
    @RequestMapping("addUser")
    public Object addUser() throws Exception {
        TbUserPO userPO = new TbUserPO();
        userPO.setUserName("momo");
        userService.insertOne(userPO);
        BasicExample e = new BasicExample(TbUserPO.class);
        BasicExample.Criteria c= e.createCriteria();
        Page page = PageHelper.startPage(1,5);

        List<TbUserPO> userPO1= userService.selectByExample(e);
        PageInfo info = new PageInfo(page.getResult());
        return new Msg().add("page",info);
    }
}
