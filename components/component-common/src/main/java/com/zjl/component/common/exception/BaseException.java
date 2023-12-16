package com.zjl.component.common.exception;

/**
 * Base Exception is the parent of all exceptions
 *
 * @author fulan.zjf 2017年10月22日 上午12:00:39
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errCode;


    BaseException(IError errorCode) {
        super(errorCode.errorMsg());
        this.errCode = errorCode.errorCode();
    }

    BaseException(IError errorCode, Throwable throwable) {
        super(errorCode.errorMsg(), throwable);
        this.errCode = errorCode.errorCode();
    }

    public String getErrCode() {
        return errCode;
    }


}
