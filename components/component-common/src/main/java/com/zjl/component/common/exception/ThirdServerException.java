package com.zjl.component.common.exception;

import com.zjl.component.common.model.ErrorInfo;

/**
 * 调用三端异常
 */
public class ThirdServerException extends BaseException {

    private static final long serialVersionUID = 1L;

    private final ErrorInfo errorInfo;

    ThirdServerException(ErrorInfo errorInfo) {
        super(CommonErrorEnum.THIRD_SERVER_ERROR);
        this.errorInfo = errorInfo;
    }

    ThirdServerException(ErrorInfo errorInfo, Throwable throwable) {
        super(CommonErrorEnum.THIRD_SERVER_ERROR, throwable);
        this.errorInfo = errorInfo;
    }

    public ErrorInfo errorInfo() {
        return this.errorInfo;
    }

}

