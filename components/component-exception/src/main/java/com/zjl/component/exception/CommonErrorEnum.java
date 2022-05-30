package com.zjl.component.exception;

public enum CommonErrorEnum implements Error {

    INVALID_PARAMETER("INVALID_PARAMETER", "参数错误"),
    INVALID_STATE("INVALID_STATE", "状态错误"),
    NOT_FOUND("NOT_FOUND", "找不到资源"),
    INNER_ERROR("INNER_ERROR", "服务内部异常信息");

    String errorCode;
    String errorMsg;

    CommonErrorEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String errorCode() {
        return this.errorCode;
    }

    @Override
    public String errorMsg() {
        return this.errorMsg;
    }

}
