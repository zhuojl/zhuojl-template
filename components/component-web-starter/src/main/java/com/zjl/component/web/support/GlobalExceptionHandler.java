package com.zjl.component.web.support;

import java.util.ArrayList;
import java.util.List;

import com.zjl.component.dto.Response;
import com.zjl.component.exception.BaseException;
import com.zjl.component.exception.BizException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<Object> handleExceptionGlobal(Throwable e, WebRequest request) throws Exception {
        LOGGER.error("SYS EXCEPTION, errorMsg:{}", e.getMessage(), e);
        return new ResponseEntity<>(Response.buildFailure("UNKNOWN_ERROR", "UNKNOWN_ERROR"),
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseException.class)
    public final ResponseEntity<Object> handleBaseException(BaseException e, WebRequest request) throws Exception {
        if (e instanceof BizException) {
            //在Debug的时候，对于BizException也打印堆栈
            if (LOGGER.isDebugEnabled()) {
                LOGGER.error("BIZ EXCEPTION, errorCode:{}, errorMsg:{}", e.getErrCode(), e.getMessage(), e);
            } else {
                LOGGER.warn("BIZ EXCEPTION, errorCode:{}, errorMsg:{}", e.getErrCode(), e.getMessage(), e);
            }
            return new ResponseEntity<>(Response.buildFailure(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        LOGGER.error("SYS EXCEPTION, errorCode:{}, errorMsg:{}", e.getErrCode(), e.getMessage(), e);
        return new ResponseEntity<>(Response.buildFailure(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
            LOGGER.error("handleExceptionInternal error", ex);
        }
        return new ResponseEntity<>(Response.buildFailure("SYS", ex.getMessage()), headers, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        return dealParamErrors(headers, allErrors);
    }

    @Override
    public ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                      WebRequest request) {
        List<ObjectError> allErrors = ex.getAllErrors();
        return dealParamErrors(headers, allErrors);
    }

    private ResponseEntity<Object> dealParamErrors(HttpHeaders headers, List<ObjectError> allErrors) {
        List<String> details = new ArrayList<>();
        for (ObjectError objectError : allErrors) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError)objectError;
                details.add(
                    fieldError.getObjectName() + "." + fieldError.getField() + ":" + fieldError.getDefaultMessage());
            } else {
                details.add(objectError.getObjectName() + ":" + objectError.getDefaultMessage());
            }
        }

        Response response = Response.buildFailure("sys.parameter", String.join(",", details));
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }
}
