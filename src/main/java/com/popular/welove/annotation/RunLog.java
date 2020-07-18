package com.popular.welove.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zmp
 * @Description: 用于打印调度运行时长日志
 * @Date: Created in 2019/7/9 10:22
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RunLog {
    String name();
}

