package com.zjl.component.web.support;

import com.zjl.component.web.support.err.handler.GlobalExceptionHandler;
import com.zjl.component.web.support.err.handler.HttpErrorHandler;
import com.zjl.component.web.support.log.ReqRespLoggingFilter;
import com.zjl.component.web.support.log.RestResultWrapper;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
    @ConditionalOnMissingBean(HttpErrorHandler.class)
    public HttpErrorHandler httpErrorHandler(ErrorAttributes errorAttributes, ServerProperties serverProperties) {
        return new HttpErrorHandler(errorAttributes, serverProperties);
    }

    @Bean
    public FilterRegistrationBean registerReqRespLoggingFilter(ReqRespLoggingFilter reqRespLoggingFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(reqRespLoggingFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }

    @Bean
    @ConditionalOnMissingBean(GlobalExceptionHandler.class)
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }

    @Bean
    @ConditionalOnMissingBean(RestResultWrapper.class)
    public RestResultWrapper restResultWrapper() {
        return new RestResultWrapper();
    }

}
