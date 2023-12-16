package com.zjl.component.common.exception;

/**
 * 客户端参数，流程等引起的通用错误 BizException is known Exception, no need retry 状态 400
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERR_CODE = "BIZ_ERROR";

    BizException(IError errorCode) {
        super(errorCode);
    }

    BizException(IError errorCode, Throwable throwable) {
        super(errorCode, throwable);
    }
}
