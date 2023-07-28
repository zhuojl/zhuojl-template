package com.zjl.component.feign;

import com.zjl.component.feign.sign.Md5SignRequestInterceptor;
import com.zjl.component.feign.sign.SignRequestInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class FeignConfiguration {


    @Bean
    @ConditionalOnMissingBean(SignRequestInterceptor.class)
    public SignRequestInterceptor signRequestInterceptor() {
        return new Md5SignRequestInterceptor();
    }

}
