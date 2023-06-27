package com.ccww.aiotlab.common.result;

public class RespResultUtil {

    //成功
    public static <T> RespResult<T>  success() {
        return getResultSuccess();
    }

    public static <T> RespResult<T>  success(String msg, Integer code) {
        RespResult<T> resultFailure = getResultSuccess();
        resultFailure.setCode(code);
        resultFailure.setMsg(msg);
        return resultFailure;
    }

    public static <T> RespResult<T>  success(String msg) {
        RespResult<T> resultFailure = getResultSuccess();
        resultFailure.setMsg(msg);
        return resultFailure;
    }

    public static <T> RespResult<T>  success(T data) {
        RespResult<T> resultFailure = getResultSuccess();
        resultFailure.setData(data);
        return resultFailure;
    }

    public static <T> RespResult<T>  success(String msg, T data) {
        RespResult<T> resultFailure = getResultSuccess();
        resultFailure.setData(data);
        resultFailure.setMsg(msg);
        return resultFailure;
    }

    //失败
    public static <T> RespResult<T>  failure() {
        return getResultFailure();
    }

    public static <T> RespResult<T>  failure(String msg, Integer code) {
        RespResult<T> resultFailure = getResultFailure();
        resultFailure.setCode(code);
        resultFailure.setMsg(msg);
        return resultFailure;
    }

    public static <T> RespResult<T>  failure(String msg) {
        RespResult<T> resultFailure = getResultFailure();
        resultFailure.setMsg(msg);
        return resultFailure;
    }

    public static <T> RespResult<T>  failure(T data) {
        RespResult<T> resultFailure = getResultFailure();
        resultFailure.setData(data);
        return resultFailure;
    }

    public static <T> RespResult<T>  failure(String msg, T data) {
        RespResult<T> resultFailure = getResultFailure();
        resultFailure.setData(data);
        resultFailure.setMsg(msg);
        return resultFailure;
    }



    private static <T> RespResult<T> getResultSuccess() {
        RespResult<T> result = new RespResult<>();
        result.setMsg(RespResultConstant.RESULT_MSG_SUCCESS);
        result.setCode(RespResultConstant.RESULT_CODE_DEFAULT);
        return result;
    }

    private static <T> RespResult<T> getResultFailure() {
        RespResult<T> result = new RespResult<>();
        result.setMsg(RespResultConstant.RESULT_MSG_FAILURE);
        result.setCode(RespResultConstant.RESULT_CODE_FAILURE);
        return result;
    }






}
