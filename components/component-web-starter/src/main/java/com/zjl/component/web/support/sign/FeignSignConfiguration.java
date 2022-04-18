package com.zjl.component.web.support.sign;

import org.springframework.context.annotation.Bean;

public class FeignSignConfiguration {
    @Bean
    public SignRequestInterceptor signRequestInterceptor() {
        return new SignRequestInterceptor();
    }

}
