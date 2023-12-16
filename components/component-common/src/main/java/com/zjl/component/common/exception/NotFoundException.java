package com.zjl.component.common.exception;

/**
 * BizException is known Exception, no need retry 状态 404
 */
public class NotFoundException extends BaseException {

    NotFoundException(IError errorCode) {
        super(errorCode);
    }

    NotFoundException(IError errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
