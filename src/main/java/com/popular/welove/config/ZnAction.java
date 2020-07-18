package com.popular.welove.config;


import com.popular.welove.entity.User;
import com.popular.welove.service.TokenManagerService;
import com.popular.welove.utils.AjaxStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: lix
 * @Description: 操作request工具类
 * @Date: 2020/2/17 12:54
 * @Version: 1.0
 */
public class ZnAction {
    @Autowired
    @Lazy
    protected TokenManagerService tokenManagerService;


    // 返回前端常量字符
    protected static final String SUCCESS = "操作成功";
    protected static final String DATA_SUBMIT_FAIL = "操作失败，请稍后重试";
    protected static final String DATA_NOT_EXIST = "数据不存在,请确认请求参数";
    protected static final String NO_AUTHORITY_ERROR = "非法操作,只有本人才能修改该数据";
    protected static final String DATA_ERROR = "参数不正确";
    protected static final String SERVICE_ERROR = "服务器异常错误";

    /**
     * 获取当前用户
     *
     * @param request
     * @return
     */
    protected User getCurrentUser(HttpServletRequest request) {
        return tokenManagerService.getCurrentUser(request);
    }

    /**
     * 获取请求携带的token
     *
     * @param request
     * @return
     */
    protected String getCurrentToken(HttpServletRequest request) {
        return tokenManagerService.getCurrentToken(request);
    }


    protected void printToPage(HttpServletResponse response, Object obj) {
        try {
            // response.setContentType("text/html;charset=UTF-8");
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().print(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @Author lix
     * @Version  1.0
     * @Description
     * @param code
     * @Date 2020/2/21 12:50
     */
    protected void reslutMethod(HttpServletResponse response, int code) {
        if(code == 200) {
            // 200 操作成功
            printToPage(response, AjaxStatus.success(SUCCESS));
        }else if(code == 501){
            // 501 操作失败，请稍后重试
            printToPage(response, AjaxStatus.success(DATA_SUBMIT_FAIL));
        }else if(code == 502){
            // 502 数据不存在
            printToPage(response, AjaxStatus.success(DATA_NOT_EXIST));
        }else if(code == 503){
            // 503 没有操作权限
            printToPage(response, AjaxStatus.success(NO_AUTHORITY_ERROR));
        }else if(code == 504){
            // 504 请求参数不正确
            printToPage(response, AjaxStatus.success(DATA_ERROR));
        }else {
            // 其它都返回 服务器异常
            printToPage(response, AjaxStatus.success(SERVICE_ERROR));
        }
    }


}
