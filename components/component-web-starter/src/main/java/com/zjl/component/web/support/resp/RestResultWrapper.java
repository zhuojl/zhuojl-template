package com.zjl.component.web.support.resp;

import com.alibaba.fastjson.JSONObject;
import com.zjl.component.common.model.Response;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 此注解针对controller层的类做增强功能，即对加了@RestController注解的类进行处理
 */
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestResultWrapper implements ResponseBodyAdvice<Object> {

    /**
     * 是否允许map，false，则不包装
     */
    @Value("${rest.response.enableWrapBody: true}")
    private Boolean enableWrapBody;

    @Override
    public boolean supports(MethodParameter returnType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        if (Boolean.FALSE.equals(enableWrapBody)) {
            return false;
        }
        // 不是response返回的场景
        return !(Objects.requireNonNull(returnType.getMethod()).getReturnType()
                .isAssignableFrom(Response.class)
                || returnType.getMethod().getReturnType().isAssignableFrom(ResponseEntity.class));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request, ServerHttpResponse response) {
        log.warn("this should not happen, please return Response type, method:{}",
                returnType.getMethod());
        if (selectedConverterType.isAssignableFrom(StringHttpMessageConverter.class)) {
            return JSONObject.toJSONString(Response.success(body));
        }
        return Response.success(body);
    }
}
