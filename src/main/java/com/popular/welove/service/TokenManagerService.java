package com.popular.welove.service;



import com.popular.welove.entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zmp
 * @Description:
 * @Date: Created in 2020/2/20 11:24
 */
 public interface TokenManagerService {
    /**
     * 创建token
     * @param userInfo
     * @return
     */
    String getToken(User userInfo);

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
    User getUserInfoByToken(String token);


   User getCurrentUser(HttpServletRequest request);

   String getCurrentToken(HttpServletRequest request);
}
