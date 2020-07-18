package com.popular.welove.interceptor;


import com.popular.welove.annotation.RunLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;


/**
 * @Author: zmp
 * @Description: RedisLock AOP
 * @Date: Created in 2019/7/8 16:01
 */
@Aspect
@Component
public class RedisLockAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("@annotation(com.popular.welove.annotation.RunLog)")
    public void redisLockCut() {
    }

    /**
     * 环绕触发 - 拦截注解
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("redisLockCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object result = null;
        long runTime = System.currentTimeMillis();

        //获取注解信息
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RunLog redisLock = method.getAnnotation(RunLog.class);

        try {

            logger.debug("[{}]定时任务开始执行...", redisLock.name());
            //执行被拦截的定时任务
            result = pjp.proceed();

        } catch (Exception ex) {
            logger.error("[{}]定时任务执行失败", redisLock.name(), ex);
        } finally {

            logger.debug("[{}]定时任务执行结束...耗时[{}毫秒]", redisLock.name(), (System.currentTimeMillis() - runTime));
        }


        return result;
    }

}
