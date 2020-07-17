package com.popular.welove.common;

/**
 * @Author: yuanbq
 * @Date: 2019/10/3 12:05
 * @Description:
 */
public class RspUtils {

    private static final int CODE_SUCCESS = 200;

    private static final int CODE_ERROR = 500;

    public static <T> ObjectRestResponse<T> successMsg(String msg) {
        return new ObjectRestResponse().setRespStatus(CODE_SUCCESS).setRespMessage(msg);
    }

    public static <T> ObjectRestResponse<T> success(T data) {
        return new ObjectRestResponse().setRespStatus(CODE_SUCCESS).data(data).rel(true);
    }

    public static <T> ObjectRestResponse<T> error(String msg) {
        return new ObjectRestResponse().setRespErrorMessage(msg).setRespStatus(CODE_ERROR);
    }

    public static <T> ObjectRestResponse<T> error(String msg, T data) {
        return new ObjectRestResponse().setRespErrorMessage(msg).data(data).setRespStatus(CODE_ERROR);
    }
}
