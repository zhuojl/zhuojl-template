package com.zjl.component.exception;

/**
 * BizException is known Exception, no need retry
 * 状态 404
 *
 */
public class NotFoundException extends BaseException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERR_CODE = "NOT_FOUND";

    public NotFoundException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    public NotFoundException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    public NotFoundException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }

}
