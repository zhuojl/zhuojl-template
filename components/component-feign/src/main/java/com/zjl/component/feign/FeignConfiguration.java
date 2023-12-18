package com.zjl.component.feign;

import com.zjl.component.common.model.Response;
import com.zjl.component.feign.decode.FeignDecoder;
import com.zjl.component.feign.decode.FeignErrorDecoder;
import com.zjl.component.feign.interceptor.context.ContextInterceptor;
import com.zjl.component.feign.interceptor.sign.Md5SignRequestInterceptor;
import com.zjl.component.feign.interceptor.sign.SignRequestInterceptor;
import com.zjl.component.feign.log.CustomFeignLoggerFactory;
import com.zjl.component.feign.sign.FeignSignCondition;
import feign.Logger;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

public class FeignConfiguration {


    @Bean
    public Decoder feignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        return new ResponseEntityDecoder(
                new FeignDecoder<>(Response.class, new SpringDecoder(messageConverters)));
    }

    @Bean
    @ConditionalOnMissingBean(SignRequestInterceptor.class)
    @Conditional(FeignSignCondition.class)
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


    @Bean
    public FeignErrorDecoder feignErrorDecoder() {
        return new FeignErrorDecoder();
    }


}
