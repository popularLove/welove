package com.popular.welove.interceptor.login;


import com.popular.welove.common.RspUtils;
import com.popular.welove.utils.AjaxStatus;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zmp
 * @Description: 登录异常处理
 * @Date: Created in 2019/7/17 16:54
 */
@ControllerAdvice
public class LoginAdvice {

    @ResponseBody
    @ExceptionHandler(value = LoginException.class)
    public JSONObject myExceptionHandler() {
        return AjaxStatus.fail("TimeOut");
    }
}
