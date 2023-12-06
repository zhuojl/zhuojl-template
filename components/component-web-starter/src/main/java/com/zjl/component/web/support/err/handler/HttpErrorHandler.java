package com.zjl.component.web.support.err.handler;

import com.zjl.component.common.exception.CommonErrorEnum;
import com.zjl.component.common.model.ErrorInfo;
import com.zjl.component.common.model.Response;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class HttpErrorHandler extends BasicErrorController {

    public HttpErrorHandler(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        super(errorAttributes, serverProperties.getError());
    }

    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        if (status == HttpStatus.NO_CONTENT) {
            return new ResponseEntity(status);
        } else {
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrCode(CommonErrorEnum.INNER_ERROR.errorCode());
            errorInfo.setErrMessage(status.value() + " " + status.getReasonPhrase());
            Response<Map<String, Object>> response = Response.failure(errorInfo);

            return new ResponseEntity(response, status);
        }
    }

}

