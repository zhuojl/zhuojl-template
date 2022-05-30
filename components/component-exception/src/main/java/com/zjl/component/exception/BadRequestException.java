package com.zjl.component.exception;

/**
 * 客户端错误，
 * 状态 400
 *
 */
public class BadRequestException extends BaseException {

    private static final long serialVersionUID = 1L;

    private static final String DEFAULT_ERR_CODE = "INVALID_PARAMETERETER";

    protected BadRequestException(String errMessage) {
        super(DEFAULT_ERR_CODE, errMessage);
    }

    protected BadRequestException(String errCode, String errMessage) {
        super(errCode, errMessage);
    }

    protected BadRequestException(String errorCode, String errMessage, Throwable e) {
        super(errorCode, errMessage, e);
    }

}
