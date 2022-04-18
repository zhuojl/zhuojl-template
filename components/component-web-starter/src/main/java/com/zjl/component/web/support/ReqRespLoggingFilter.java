package com.zjl.component.web.support;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

/**
 * 用于打印http请求和响应
 */
@Order(Ordered.HIGHEST_PRECEDENCE)        //设置优先级，数值越低优先级越高
@WebFilter(filterName = "reqRespLoggingFilter", urlPatterns = "/*")    //过滤路径
public class ReqRespLoggingFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReqRespLoggingFilter.class);

    public ReqRespLoggingFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        handleTraceId(request);

        if (LOGGER.isInfoEnabled()) {
            ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest)request);
            ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(
                (HttpServletResponse)response);
            LOGGER.info("common log request, method:{}, uri:{}, param:{}, body:{}", requestWrapper.getMethod(),
                requestWrapper.getRequestURI(), JSONObject.toJSON(requestWrapper.getParameterMap()),
                new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));

            chain.doFilter(requestWrapper, responseWrapper);

            LOGGER.info("common log response, method:{}, uri:{}, body:{}", requestWrapper.getMethod(),
                requestWrapper.getRequestURI(),
                new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));

            // need this to copy back to original response
            responseWrapper.copyBodyToResponse();

        } else {
            chain.doFilter(request, response);
        }
    }

    private void handleTraceId(ServletRequest request) {
        // 在没有sleuth的场景下，手动注入traceId，方便日志排查
    }

    @Override
    public void destroy() {

    }
}

