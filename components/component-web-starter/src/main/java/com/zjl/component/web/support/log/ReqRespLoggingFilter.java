package com.zjl.component.web.support.log;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

/**
 * 用于打印http请求和响应
 * 通用请求打印，也可以使用 CommonsRequestLoggingFilter
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 90)        //设置优先级，数值越低优先级越高
// WebFilter过滤路径设置无效，通过FilterRegistrationBean配置
public class ReqRespLoggingFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReqRespLoggingFilter.class);


    /**
     * 配置项：
     * 1. 白名单
     * 2. 日志长度
     * <p>
     * 最长日志长度，可配置
     */
    private Integer maxLogLength = 10000;

    public ReqRespLoggingFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        handleTraceId(request);

        if (!LOGGER.isInfoEnabled()) {
            chain.doFilter(request, response);
            return;
        }

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request, maxLogLength);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        // XXX 删除requestBody因为，在此提前读取，「在序列化」的时候会报错；在序列化后，可以通过 getContentAsByteArray 获取
        LOGGER.info("common log request, method:{}, uri:{},\t requestParam:{}",
            requestWrapper.getMethod(), requestWrapper.getRequestURI(), JSONObject.toJSONString(requestWrapper.getParameterMap()));
        long timeMillStart = System.currentTimeMillis();

        chain.doFilter(requestWrapper, responseWrapper);

        LOGGER.info("common log response, method:{}, uri:{}, time:{}, requestBody:{}, responseBody:{}",
            requestWrapper.getMethod(), requestWrapper.getRequestURI(), System.currentTimeMillis() - timeMillStart,
            requestPayloadBody(requestWrapper), responsePayloadBody(responseWrapper));

        // need this to copy back to original response
        responseWrapper.copyBodyToResponse();
    }

    private void handleTraceId(ServletRequest request) {
        // 在没有sleuth的场景下，手动注入traceId，方便日志排查
    }

    private String requestPayloadBody(ContentCachingRequestWrapper wrapper) {
        return getString(wrapper.getContentAsByteArray(), wrapper.getCharacterEncoding());
    }

    private String responsePayloadBody(ContentCachingResponseWrapper wrapper) {
        return getString(wrapper.getContentAsByteArray(), "UTF-8");
    }

    private String getString(byte[] contentAsByteArray, String characterEncoding) {
        if (Objects.isNull(contentAsByteArray)) {
            return null;
        }
        if (contentAsByteArray.length > 0) {
            int length = Math.min(contentAsByteArray.length, maxLogLength);
            try {
                return new String(contentAsByteArray, 0, length, characterEncoding);
            } catch (UnsupportedEncodingException ex) {
                return "[unknown]";
            }
        }
        return null;
    }

    @Override
    public void destroy() {

    }
}

