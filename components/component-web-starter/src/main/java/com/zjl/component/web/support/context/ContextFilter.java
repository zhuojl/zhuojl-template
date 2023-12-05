//package com.zjl.component.web.support.context;
//
//import com.zjl.component.common.context.RequestContext;
//import com.zjl.component.common.context.RequestContextHolder;
//import com.zjl.component.web.support.context.util.IpUtil;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//
//import javax.servlet.*;
//import java.io.IOException;
//
///**
// * 用于打印http请求和响应
// * 通用请求打印，也可以使用 CommonsRequestLoggingFilter
// */
//@Order(Ordered.HIGHEST_PRECEDENCE + 80)        //设置优先级，数值越低优先级越高
//// WebFilter过滤路径设置无效，通过FilterRegistrationBean配置
//public class ContextFilter implements Filter {
//    private static final Logger LOGGER = LoggerFactory.getLogger(ContextFilter.class);
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//        throws IOException, ServletException {
//
//        // 隔离
//        String traceId = "";
//        String realIp = IpUtil.getFirstIpAddr(request);
//        String userId = "";
//        try {
//            RequestContext requestContext = RequestContext.builder().traceId(traceId).realIp(realIp).userId(userId).build();
//            RequestContextHolder.setRequestContextHolder(requestContext);
//            chain.doFilter(request, response);
//        } finally {
//            RequestContextHolder.clear();
//        }
//    }
//
//
//    @Override
//    public void destroy() {
//
//    }
//}
//
