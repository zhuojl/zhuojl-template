package com.zjl.component.dto;

import com.zjl.component.exception.BaseException;

/**
 * Response to caller
 *
 * @author fulan.zjf 2017年10月21日 下午8:53:17
 */
public class Response<T> extends DTO {

    private static final long serialVersionUID = 1L;

    private boolean success;

    private String errCode;

    private String errMessage;

    private T data;


    public static <T> Response of(T t) {
        Response response = new Response();
        response.setSuccess(true);
        response.setData(t);
        return response;
    }

    public static Response buildFailure(String errCode, String errMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(errCode);
        response.setErrMessage(errMessage);
        return response;
    }

    public static Response buildFailure(BaseException baseException) {
        Response response = new Response();
        response.setSuccess(false);
        response.setErrCode(baseException.getErrCode());
        response.setErrMessage(baseException.getErrCode());
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

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
