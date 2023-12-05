package com.zjl.component.common.exception;

public class UnauthorizedException extends BaseException {

    UnauthorizedException(IError errorCode) {
        super(errorCode);
    }

    UnauthorizedException(IError errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
