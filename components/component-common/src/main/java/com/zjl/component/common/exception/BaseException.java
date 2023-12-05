package com.zjl.component.common.exception;

import java.util.Map;

/**
 * Base Exception is the parent of all exceptions
 *
 * @author fulan.zjf 2017年10月22日 上午12:00:39
 */
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String errCode;

    // XXX 动态参数预留
    private Map<String, Object> errorParam;


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

    public Map<String, Object> getErrorParam() {
        return errorParam;
    }


}
