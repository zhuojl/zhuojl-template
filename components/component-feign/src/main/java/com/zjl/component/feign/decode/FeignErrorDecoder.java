package com.zjl.component.feign.decode;


import com.zjl.component.common.exception.CommonErrorEnum;
import com.zjl.component.common.exception.ExceptionFactory;
import com.zjl.component.common.model.ErrorInfo;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.util.Objects;


public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String body = getBodyBytes(response);

        String message = String.format("status %s reading %s", response.status(), methodKey);

        ErrorInfo thirdServerErrorInfo = ErrorInfo.builder()
            .errCode(CommonErrorEnum.THIRD_SERVER_ERROR.errorCode())
            .errMessage(message)
            .errSys(methodKey)
            .errDetail(body)
            .build();

        return ExceptionFactory.thirdServerException(thirdServerErrorInfo);

    }


    private static String getBodyBytes(Response response) {

        try {
            if (response.body() != null) {
                byte[] bytes = Util.toByteArray(response.body().asInputStream());
                if (Objects.nonNull(bytes)) {
                    return new String(bytes, "UTF-8");
                }
            }
        } catch (IOException ignored) { // NOPMD
        }
        throw ExceptionFactory.sysException(CommonErrorEnum.INNER_ERROR);
    }
}
