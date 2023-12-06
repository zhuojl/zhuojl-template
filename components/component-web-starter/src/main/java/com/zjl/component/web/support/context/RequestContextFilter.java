package com.zjl.component.web.support.context;

import com.zjl.component.common.CommonConstants;
import com.zjl.component.common.context.RequestContext;
import com.zjl.component.common.context.RequestContextHolder;
import org.apache.catalina.connector.RequestFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

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
            String realIp = getFirstIpAddr(requestFacade);
            String userId = requestFacade.getHeader(CommonConstants.USER_ID);
            RequestContext requestContext = RequestContext.builder().realIp(realIp).userId(userId).build();
            RequestContextHolder.setRequestContextHolder(requestContext);

            chain.doFilter(request, response);
        } finally {
            RequestContextHolder.clear();

        }
    }



    private static String getIpAddr(RequestFacade request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            // 根据网卡取本机配置的IP
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                try {
                    InetAddress inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    LOGGER.error("根据网卡获取本机配置的IP异常", e);
                }

            }
        }
        return ipAddress;
    }

    private static String getFirstIpAddr(RequestFacade request) {
        String ipAddress = getIpAddr(request);
        // 对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
        if (ipAddress != null && ipAddress.indexOf(",") > 0) {
            ipAddress = ipAddress.split(",")[0];
        }
        return ipAddress;
    }

    @Override
    public void destroy() {

    }
}
