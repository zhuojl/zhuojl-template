package com.zjl.component.common.model;


import com.zjl.component.common.exception.BaseException;
import com.zjl.component.common.exception.IError;
import java.io.Serializable;
import lombok.Data;

/**
 * Response to caller
 *
 * @author fulan.zjf 2017年10月21日 下午8:53:17
 */
@Data
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private ErrorInfo error;

    private T data;

    public static <T> Response<T> success(T t) {
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

}
