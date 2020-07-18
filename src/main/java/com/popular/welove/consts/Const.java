package com.popular.welove.consts;

/**
 * @Author: zmp
 * @Description:
 * @Date: Created in 2020/2/20 12:41
 */
public interface Const {
    /**
     * tokenRedis前缀
     */
    String REDIS_TOKEN_PRE = "token:";

    /**
     * token redis缓存时间3600秒(1小时)
     */
    long TOKEN_CACHE_TIME = 3600l*24*10;

    /**
     * 存放鉴权信息的Header名称
     */
    String HEAD_TOKEN_KEY = "token";

    /**
     * 应用redis key
     */
    String APP_REDIS_KEY = "_app_reg_key_";

    /**
     * 手机验证码redis前缀
     */
    String PHONE_V_CODE_PRE = "phoneCode:";

    /**
     * 邮箱验证码redis前缀
     */
    String MAIL_V_CODE_PRE = "mailCode:";

    /**
     * 小程序用户身份-普通求职用户
     */
    Integer NORMAL_USER_ROLE = 3;

    /**
     * 小程序用户身份-普通员工用户
     */
    Integer EMPLOYEE_ROLE = 4;

    /**
     * 小程序用户身份-企业管理员
     */
    Integer ENTERPRISE_ADMINS_ROLE = 5;

    /**
     * 小程序用户身份-企业拥有者
     */
    Integer ENTERPRISE_OWNER_ROLE = 6;
}
