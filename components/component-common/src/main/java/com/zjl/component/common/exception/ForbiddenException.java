package com.zjl.component.common.exception;

/**
 * 未授权异常（无资源权限）
 *
 */
public class ForbiddenException extends BaseException {

    ForbiddenException(IError errorCode) {
        super(errorCode);
    }

    ForbiddenException(IError errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
