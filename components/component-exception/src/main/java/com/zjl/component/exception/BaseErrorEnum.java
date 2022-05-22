package com.zjl.component.exception;


public enum BaseErrorEnum implements Error{
    PARAM_MISS_ERROR("PARAM_ERROR","参数错误"),
    ;


    String errorCode;
    String errorMsg;

    BaseErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMsg() {
        return this.errorMsg;
    }
}
