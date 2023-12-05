package com.zjl.component.web.support.err.handler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import com.zjl.component.common.model.ErrorInfo;
import com.zjl.component.common.model.Response;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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

            Map<String, Object> body = this.getErrorAttributes(request,
                this.isIncludeStackTrace(request, MediaType.ALL));
            ErrorInfo errorInfo = new ErrorInfo();
            errorInfo.setErrCode("SYS." + status.value());
            errorInfo.setErrMessage(status.getReasonPhrase());
            errorInfo.setErrDetail(body);
            Response<Map<String, Object>> response = Response.buildFailure(errorInfo);

            return new ResponseEntity(response, status);
        }
    }

}

