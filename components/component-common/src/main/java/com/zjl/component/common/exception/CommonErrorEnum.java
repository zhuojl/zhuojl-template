package com.zjl.component.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum CommonErrorEnum implements IError {

    INVALID_PARAMETER("INVALID_PARAMETER", "参数错误"),
    INVALID_STATE("INVALID_STATE", "状态错误"),
    NOT_FOUND("NOT_FOUND", "找不到资源"),
    INNER_ERROR("INNER_ERROR", "服务内部异常信息"),
    THIRD_SERVER_ERROR("THIRD_SERVER_ERROR", "服务内部异常信息");

    String errorCode;
    String errorMsg;


    @Override
    public String errorCode() {
        return this.errorCode;
    }

    @Override
    public String errorMsg() {
        return this.errorMsg;
    }

}
