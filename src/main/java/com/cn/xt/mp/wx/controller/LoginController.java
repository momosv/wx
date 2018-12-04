package com.cn.xt.mp.wx.controller;




import com.cn.xt.mp.base.entity.Msg;
import com.cn.xt.mp.base.interfaces.AuthIgnore;
import com.cn.xt.mp.base.util.Constants;
import com.cn.xt.mp.wx.baseController.BaseController;
import com.cn.xt.mp.wx.model.UserInfoPO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;

@AuthIgnore
@RestController
public class LoginController extends BaseController {


    @RequestMapping("/login")
    public Object login(UserInfoPO user, String id, HttpServletRequest servletRequest){
        String token = authManager.signIn(user);
        servletRequest.getSession().setAttribute(Constants.USER_TOKEN,token);
        return Msg.success("success").add(Constants.USER_TOKEN,token);
    }

    @RequestMapping("/lo")
    public Object lo() throws AuthException {
        UserInfoPO user = getUser();
        return Msg.success("success").add("user",user);
    }

}
