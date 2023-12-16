package com.zjl.component.web.support.context;

import com.zjl.component.common.CommonConstants;
import java.io.IOException;
import java.util.UUID;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.catalina.connector.RequestFacade;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 用于打印http请求和响应 通用请求打印，也可以使用 CommonsRequestLoggingFilter
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 81)        //设置优先级，数值越低优先级越高
// WebFilter过滤路径设置无效，通过FilterRegistrationBean配置
public class MDCContextFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MDCContextFilter.class);

    private static String getTraceId(RequestFacade request) {
        String traceId = request.getHeader(CommonConstants.TRACE_ID);
        if (!Strings.isEmpty(traceId)) {
            return traceId;
        }
        return createTraceId();

    }

    private static String createTraceId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        RequestFacade requestFacade = (RequestFacade) request;

        try {
            String userId = requestFacade.getHeader(CommonConstants.USER_ID);
            String traceId = getTraceId(requestFacade);

            MDC.put(CommonConstants.TRACE_ID, traceId);
            MDC.put(CommonConstants.USER_ID, userId);
            MDC.put(CommonConstants.URI, requestFacade.getRequestURI());

            chain.doFilter(request, response);
        } finally {
            MDC.remove(CommonConstants.TRACE_ID);
            MDC.remove(CommonConstants.USER_ID);
            MDC.remove(CommonConstants.URI);
        }
    }

    @Override
    public void destroy() {

    }
}

