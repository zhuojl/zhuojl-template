package com.zjl.component.dto;

import com.zjl.component.exception.BaseException;
import com.zjl.component.exception.Error;

/**
 * Response to caller
 *
 * @author fulan.zjf 2017年10月21日 下午8:53:17
 */
public class Response<T> extends DTO {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private ErrorInfo error;

    private T data;

    public static <T> Response of(T t) {
        Response response = new Response();
        response.setSuccess(true);
        response.setData(t);
        return response;
    }

    public static Response buildFailure(Error error) {
        Response response = new Response();
        response.setSuccess(false);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrCode(error.errorCode());
        errorInfo.setErrMessage(error.errorMsg());
        response.setError(errorInfo);
        return response;
    }

    public static Response buildFailure(String errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(false);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrCode(errCode);
        errorInfo.setErrMessage(errMessage);
        response.setError(errorInfo);
        return response;
    }
    public static Response buildFailure(ErrorInfo errorInfo) {
        Response response = new Response();
        response.setSuccess(false);
        response.setError(errorInfo);
        return response;
    }

    public static Response buildFailure(BaseException baseException) {
        Response response = new Response();
        response.setSuccess(false);
        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setErrCode(baseException.getErrCode());
        errorInfo.setErrMessage(baseException.getMessage());
        response.setError(errorInfo);
        return response;
    }

    public static Response buildSuccess() {
        Response response = new Response();
        response.setSuccess(true);
        return response;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
