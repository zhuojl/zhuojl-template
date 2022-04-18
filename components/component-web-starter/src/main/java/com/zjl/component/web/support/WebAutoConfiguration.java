package com.zjl.component.web.support;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
public class WebAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ReqRespLoggingFilter.class)
    public ReqRespLoggingFilter reqRespLoggingFilter() {
        return new ReqRespLoggingFilter();
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
