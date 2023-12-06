package com.zjl.component.feign;

import com.zjl.component.feign.interceptor.context.ContextInterceptor;
import com.zjl.component.feign.interceptor.sign.Md5SignRequestInterceptor;
import com.zjl.component.feign.interceptor.sign.SignRequestInterceptor;
import com.zjl.component.feign.log.CustomFeignLoggerFactory;
import feign.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {


    @Bean
    @ConditionalOnMissingBean(SignRequestInterceptor.class)
    public SignRequestInterceptor signRequestInterceptor() {
        return new Md5SignRequestInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean(FeignLoggerFactory.class)
    public FeignLoggerFactory feignLoggerFactory() {
        return new CustomFeignLoggerFactory();
    }
    @Bean
    @ConditionalOnMissingBean(ContextInterceptor.class)
    public ContextInterceptor contextInterceptor() {
        return new ContextInterceptor();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


}
