package com.cn.xt.mp.base.baseController;



import com.cn.xt.mp.base.tokenManager.impl.AuthManager;
import com.cn.xt.mp.base.entity.UserInfoPO;
import com.cn.xt.mp.vo.CompanyUserVO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.message.AuthException;

public class BaseController {
    @Autowired
    public AuthManager authManager;

    public UserInfoPO getAgentUser() throws AuthException {
      return  authManager.getUserInfo();
    }

    public CompanyUserVO getCompanyUser() throws Exception {
        return  authManager.getCompanyUser();
    }
}
