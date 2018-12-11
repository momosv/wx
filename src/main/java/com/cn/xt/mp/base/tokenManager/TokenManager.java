package com.cn.xt.mp.base.tokenManager;


import com.cn.xt.mp.base.entity.UserInfoPO;

public interface TokenManager {

    /**
     * 创建token
     * @param userInfo
     * @return
     */
    String getToken(UserInfoPO userInfo);

    /**
     * 刷新用户
     * @param token
     */
    void refreshUserToken(String token);

    /**
     * 用户退出登陆
     * @param token
     */
    void loginOff(String token);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    UserInfoPO getUserInfoByToken(String token);

}
