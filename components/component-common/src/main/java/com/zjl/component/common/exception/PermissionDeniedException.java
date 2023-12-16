package com.zjl.component.common.exception;

/**
 * 无权限操作 状态 403
 */
public class PermissionDeniedException extends BaseException {


    PermissionDeniedException(IError errorCode) {
        super(errorCode);
    }

    PermissionDeniedException(IError errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
