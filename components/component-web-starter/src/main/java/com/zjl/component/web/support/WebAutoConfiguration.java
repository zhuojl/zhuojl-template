package com.zjl.component.web.support;

import com.zjl.component.web.support.context.MDCContextFilter;
import com.zjl.component.web.support.context.RequestContextFilter;
import com.zjl.component.web.support.err.handler.GlobalExceptionHandler;
import com.zjl.component.web.support.err.handler.HttpErrorHandler;
import com.zjl.component.web.support.log.ReqRespLoggingFilter;
import com.zjl.component.web.support.resp.RestResultWrapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @ Description   :
 * @ Author        :  Frank Zhang
 * @ CreateDate    :  2020/11/09
 * @ Version       :  1.0
 */
@Configuration
@EnableAspectJAutoProxy
@AutoConfigureBefore(ErrorMvcAutoConfiguration.class)
public class WebAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ReqRespLoggingFilter.class)
    public ReqRespLoggingFilter reqRespLoggingFilter() {
        return new ReqRespLoggingFilter();
    }

    @Bean
    @ConditionalOnMissingBean(RequestContextFilter.class)
    public RequestContextFilter contextFilter() {
        return new RequestContextFilter();
    }

    @Bean
    @ConditionalOnMissingBean(MDCContextFilter.class)
    public MDCContextFilter mdcContextFilter() {
        return new MDCContextFilter();
    }

    @Bean
    @ConditionalOnMissingBean(HttpErrorHandler.class)
    public HttpErrorHandler httpErrorHandler(ErrorAttributes errorAttributes,
            ServerProperties serverProperties) {
        return new HttpErrorHandler(errorAttributes, serverProperties);
    }

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    public RestResultWrapper restResultWrapper() {
        return new RestResultWrapper();
    }

}
