package com.popular.welove.interceptor;

import com.popular.welove.annotation.AuthIgnore;
import com.popular.welove.interceptor.login.LoginException;
import com.popular.welove.redis.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

import static com.popular.welove.consts.Const.*;


/**
 * @Author: zmp
 * @Description: 检查应用或用户请求是否有token
 * @Date: Created in 2019/6/24 15:24
 */
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuthInterceptor.class);

    @Autowired
    RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            final Class<?> clazz = handlerMethod.getBeanType();
            final Method method = handlerMethod.getMethod();
            if (!clazz.isAnnotationPresent(AuthIgnore.class) && !method.isAnnotationPresent(AuthIgnore.class)) {

                String appRegKey = request.getHeader(APP_REDIS_KEY);

                //应用访问
                if (StringUtils.isEmpty(appRegKey) || redisUtil.get(appRegKey) == null) {

                    //用户访问
                    String token = request.getHeader(HEAD_TOKEN_KEY);
                    LOGGER.debug("Get token from request is {}", token);
                    Object userId = redisUtil.get(REDIS_TOKEN_PRE + token);
                    if (userId == null) {
                        LOGGER.info("登录超时或没有登录，页面退出!,uri[{}]", request.getRequestURI());
                        throw new LoginException();
                    } else {
                        //刷新token缓存时间
                        redisUtil.expire(REDIS_TOKEN_PRE + token, TOKEN_CACHE_TIME);
                    }
                }
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // nothing to do
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // nothing to do
    }

}
