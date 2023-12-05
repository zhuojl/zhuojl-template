package com.zjl.component.common.exception;

/**
 * 客户端错误，
 * 状态 400
 *
 */
public class BadRequestException extends BaseException {

    BadRequestException(IError errorCode) {
        super(errorCode);
    }

    BadRequestException(IError errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
