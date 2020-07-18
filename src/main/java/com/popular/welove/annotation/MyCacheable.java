package com.popular.welove.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author lidesong
 * @Description 整合Springboot-reids缓存，添加失效时间条件
 * @Date 14:06 2020-03-24
 * @Param
 * @return
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyCacheable {

    /**
     * 缓存key
     *
     * @return
     */
    String key();

    /**
     * 是否缓存空值
     *
     * @return
     */
    boolean cacheNull() default false;

    /**
     * 生存时间，单位是秒，默认为-1(永不过期)
     *
     * @return
     */
    int ttl() default -1;

    /**
     * 生存状态
     * <p>
     * true:每访问一次，将刷新存活时间
     * <p>
     * false:不刷新存活时间，时间一到就清除
     *
     * @return
     */
    boolean state() default true;

}
