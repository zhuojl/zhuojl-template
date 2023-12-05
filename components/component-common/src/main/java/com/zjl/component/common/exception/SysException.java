package com.zjl.component.common.exception;

/**
 * System Exception is unexpected Exception, retry might work again
 *
 * @author Danny.Lee 2018/1/27
 */
public class SysException extends BaseException {

    SysException(IError errorCode) {
        super(errorCode);
    }

    SysException(IError errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
