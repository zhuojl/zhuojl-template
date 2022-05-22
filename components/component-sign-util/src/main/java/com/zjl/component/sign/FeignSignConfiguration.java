package com.zjl.component.sign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class FeignSignConfiguration {
    @Bean
    public Md5SignRequestInterceptor signRequestInterceptor() {
        return new Md5SignRequestInterceptor();
    }

    @Bean
    @ConditionalOnMissingBean
    public Md5Signer md5Signer() {
        return new Md5Signer();
    }

}
