package com.zjl.component.web.support.context;

import com.zjl.component.common.CommonConstants;
import com.zjl.component.common.context.RequestContext;
import com.zjl.component.common.context.RequestContextHolder;
import com.zjl.component.web.support.context.util.IpUtil;
import com.zjl.component.web.support.context.util.TraceIdUtil;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;

/**
 * 用于打印http请求和响应
 * 通用请求打印，也可以使用 CommonsRequestLoggingFilter
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 80)        //设置优先级，数值越低优先级越高
// WebFilter过滤路径设置无效，通过FilterRegistrationBean配置
public class RequestContextFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestContextFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        RequestFacade requestFacade = (RequestFacade) request;
        try {
            String realIp = IpUtil.getFirstIpAddr(requestFacade);
            String userId = requestFacade.getHeader(CommonConstants.USER_ID);
            RequestContext requestContext = RequestContext.builder().realIp(realIp).userId(userId).build();
            RequestContextHolder.setRequestContextHolder(requestContext);

            chain.doFilter(request, response);
        } finally {
            RequestContextHolder.clear();

        }
    }


    @Override
    public void destroy() {

    }
}

