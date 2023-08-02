package com.zjl.component.feign;

import com.zjl.component.feign.log.CustomFeignLoggerFactory;
import com.zjl.component.feign.sign.Md5SignRequestInterceptor;
import com.zjl.component.feign.sign.SignRequestInterceptor;
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
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


}
