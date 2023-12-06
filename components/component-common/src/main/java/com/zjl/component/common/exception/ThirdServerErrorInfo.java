package com.zjl.component.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 三方系统异常信息
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ThirdServerErrorInfo {
    private Integer httpStatus;
    private String simpleMsg;
    private String responseBody;
    private String system;

}

