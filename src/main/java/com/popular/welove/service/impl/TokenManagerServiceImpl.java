package com.popular.welove.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.popular.welove.entity.User;
import com.popular.welove.interceptor.login.LoginException;
import com.popular.welove.redis.RedisUtil;
import com.popular.welove.service.TokenManagerService;
import com.popular.welove.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

import static com.popular.welove.consts.Const.*;


/**
 * @Author: zmp
 * @Description:
 * @Date: Created in 2020/2/20 11:25
 */
@Service
public class TokenManagerServiceImpl implements TokenManagerService {

    @Autowired
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;

    /**
     * 生成token
     * @param userInfo
     * @return
     */
    @Override
    public String getToken(User userInfo) {
        String token = UUID.randomUUID().toString();
        redisUtil.set(REDIS_TOKEN_PRE + token, userInfo.getId(), TOKEN_CACHE_TIME);
        return token;
    }

    /**
     * 刷新token时间
     * @param token
     */
    @Override
    public void refreshUserToken(String token) {
        redisUtil.expire(REDIS_TOKEN_PRE + token, TOKEN_CACHE_TIME);
    }

    /**
     * 清理token
     * @param token
     */
    @Override
    public void loginOff(String token) {
        redisUtil.del(REDIS_TOKEN_PRE + token);
    }

    @Override
    public User getUserInfoByToken(String token) {
        Object o = redisUtil.get(REDIS_TOKEN_PRE + token);
        if (o != null) {
            EntityWrapper ew = new EntityWrapper();
            ew.eq("id", o).eq("status",1);
            return userService.selectOne(ew);
        }
        return null;
    }

    /**
     * 获取请求携带的token
     * @param request
     * @return
     */
    @Override
    public String getCurrentToken(HttpServletRequest request){
        return request.getHeader(HEAD_TOKEN_KEY);
    }

    /**
     * 获取当前用户
     * @param request
     * @return
     */
    @Override
    public User getCurrentUser(HttpServletRequest request) {
        String token = request.getHeader(HEAD_TOKEN_KEY);
        if (StringUtils.isNotEmpty(token)) {
            return getUserInfoByToken(token);
        }
        throw new LoginException();
    }
}
