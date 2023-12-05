package com.zjl.component.common.model;


import com.zjl.component.common.exception.BaseException;
import com.zjl.component.common.exception.IError;

/**
 * Response to caller
 *
 * @author fulan.zjf 2017年10月21日 下午8:53:17
 */
public class Response<T> extends DTO {

    private static final long serialVersionUID = 1L;

    private ErrorInfo error;

    private T data;

    public static <T> Response success(T t) {
        Response<T> response = new Response<>();
        response.setData(t);
        return response;
    }

    public static Response failure(IError error) {
        Response response = new Response();
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrCode(error.errorCode());
        errorInfo.setErrMessage(error.errorMsg());
        response.setError(errorInfo);
        return response;
    }

    public static Response failure(String errCode, String errMessage) {
        Response response = new Response();
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrCode(errCode);
        errorInfo.setErrMessage(errMessage);
        response.setError(errorInfo);
        return response;
    }
    public static Response failure(ErrorInfo errorInfo) {
        Response response = new Response();
        response.setError(errorInfo);
        return response;
    }

    public static Response failure(BaseException baseException) {
        Response response = new Response();
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrCode(baseException.getErrCode());
        errorInfo.setErrMessage(baseException.getMessage());
        response.setError(errorInfo);
        return response;
    }

    public static Response buildSuccess() {
        return new Response();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ErrorInfo getError() {
        return error;
    }

    public void setError(ErrorInfo error) {
        this.error = error;
    }
}
