package com.zjl.component.feign.interceptor.context;

import com.zjl.component.common.CommonConstants;
import com.zjl.component.common.context.RequestContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.catalina.connector.RequestFacade;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.MDC;

import java.util.UUID;

public class ContextInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        String traceId = getTraceId();
        String userId = RequestContextHolder.getUserId();
        template.header(CommonConstants.TRACE_ID, traceId);
        template.header(CommonConstants.USER_ID, userId);
    }

    public static String getTraceId() {
        String traceId = MDC.get(CommonConstants.TRACE_ID);
        if (!Strings.isEmpty(traceId)) {
            return traceId;
        }
        return createTraceId();

    }

    public static String createTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
