package com.cn.xt.mp.wx.baseController;



import com.cn.xt.mp.wx.component.impl.AuthManager;
import com.cn.xt.mp.wx.model.UserInfoPO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.message.AuthException;

public class BaseController {
    @Autowired
    public AuthManager authManager;

    public UserInfoPO getUser() throws AuthException {
      return  authManager.getUserInfo();
    }
}
