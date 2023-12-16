package com.zjl.component.web.support.err.handler;

import com.zjl.component.common.exception.BadRequestException;
import com.zjl.component.common.exception.BizException;
import com.zjl.component.common.exception.CommonErrorEnum;
import com.zjl.component.common.exception.NotFoundException;
import com.zjl.component.common.exception.PermissionDeniedException;
import com.zjl.component.common.exception.SysException;
import com.zjl.component.common.exception.ThirdServerException;
import com.zjl.component.common.model.Response;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BizException.class)
    public final ResponseEntity<Object> handleBizException(BizException e, WebRequest request)
            throws Exception {
        //在Debug的时候，对于BizException也打印堆栈
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("handleBizException EXCEPTION: {}, errorMsg:{}",
                    e.getClass().getSimpleName(), e.getMessage(), e);
        }
        return new ResponseEntity<>(Response.failure(e), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException e,
            WebRequest request) throws Exception {
        //在Debug的时候，对于BizException也打印堆栈
        if (LOGGER.isErrorEnabled()) {
            LOGGER.error("handleBadRequestException EXCEPTION: {}, errorMsg:{}",
                    e.getClass().getSimpleName(), e.getMessage(), e);
        }
        return new ResponseEntity<>(Response.failure(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException e,
            WebRequest request) throws Exception {
        return new ResponseEntity<>(Response.failure(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public final ResponseEntity<Object> handlePermissionDeniedException(PermissionDeniedException e,
            WebRequest request) throws Exception {
        return new ResponseEntity<>(Response.failure(e), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(SysException.class)
    public final ResponseEntity<Object> handleSysException(SysException e, WebRequest request)
            throws Exception {
        LOGGER.error("SYS EXCEPTION, errorCode:{}, errorMsg:{}", e.getErrCode(), e.getMessage(), e);
        return new ResponseEntity<>(Response.failure(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    @ExceptionHandler(FeignException.class)
//    public final ResponseEntity<Object> handleFeignException(FeignException e, WebRequest request) throws Exception {
//        LOGGER.error("handleThrowableGlobal EXCEPTION: {}, errorMsg:{}", e.getClass().getSimpleName(), e.getMessage(), e);
//        return new ResponseEntity<>(Response.failure(CommonErrorEnum.INNER_ERROR.errorCode(), e.contentUTF8()), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(Throwable.class)
    public final ResponseEntity<Object> handleThrowableGlobal(Throwable e, WebRequest request)
            throws Exception {
        LOGGER.error("handleThrowableGlobal EXCEPTION: {}, errorMsg:{}",
                e.getClass().getSimpleName(), e.getMessage(), e);
        return new ResponseEntity<>(Response.failure(CommonErrorEnum.INNER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptionGlobal(Exception e, WebRequest request)
            throws Exception {
        LOGGER.error("handleExceptionGlobal EXCEPTION: {}, errorMsg:{}",
                e.getClass().getSimpleName(), e.getMessage(), e);
        return new ResponseEntity<>(Response.failure(CommonErrorEnum.INNER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public final ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException e,
            WebRequest request) throws Exception {
        LOGGER.error("handleHttpClientErrorException EXCEPTION: {}, errorMsg:{}",
                e.getClass().getSimpleName(), e.getMessage(), e);
        return new ResponseEntity<>(Response.failure(CommonErrorEnum.INNER_ERROR),
                e.getStatusCode());
    }


    @ExceptionHandler(ThirdServerException.class)
    public final ResponseEntity<Object> handleThirdServerException(ThirdServerException e,
            WebRequest request) throws Exception {
        LOGGER.error("handleHttpClientErrorException EXCEPTION: {}, errorMsg:{}",
                e.getClass().getSimpleName(), e.getMessage(), e);

        return new ResponseEntity<>(Response.failure(e.errorInfo()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
            HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }

        // METHOD_NOT_ALLOWED
        LOGGER.error("handleExceptionInternal, EXCEPTION:{}, status:{}",
                ex.getClass().getSimpleName(), status, ex);

        return new ResponseEntity<>(Response.failure(CommonErrorEnum.INNER_ERROR), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        return dealParamErrors(headers, allErrors);
    }

    @Override
    public ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<ObjectError> allErrors = ex.getAllErrors();
        return dealParamErrors(headers, allErrors);
    }

    private ResponseEntity<Object> dealParamErrors(HttpHeaders headers,
            List<ObjectError> allErrors) {
        List<String> details = new ArrayList<>();
        for (ObjectError objectError : allErrors) {
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                details.add(
                        fieldError.getObjectName() + "." + fieldError.getField() + ":"
                                + fieldError.getDefaultMessage());
            } else {
                details.add(objectError.getObjectName() + ":" + objectError.getDefaultMessage());
            }
        }

        Response response = Response.failure(CommonErrorEnum.INVALID_PARAMETER.errorCode(),
                String.join(",", details));
        return new ResponseEntity<>(response, headers, HttpStatus.BAD_REQUEST);
    }
}
