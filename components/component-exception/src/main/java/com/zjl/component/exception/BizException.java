package com.zjl.component.exception;

/**
 * 客户端参数，流程等引起的通用错误
 * BizException is known Exception, no need retry
 * 状态 400
 *
 */
public class BizException extends BaseException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERR_CODE = "BIZ_ERROR";

    public BizException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    protected BizException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    protected BizException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }

}
