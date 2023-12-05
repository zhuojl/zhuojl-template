package com.zjl.archetype.web.infra.exception;

import com.zjl.component.common.exception.IError;

/**
 * 在此定义业务异常
 */
public enum BizErrorEnum implements IError {

    COMPANY_NAME_REPEAT("BIZ.INVALID_PARAMETER.COMPANY_NAME_REPEAT", "公司名冲突")
    ;

    String errorCode;
    String errorMsg;

    BizErrorEnum(String errorCode, String errorMsg) {
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
