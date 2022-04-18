package com.zjl.archetype.web.infra.config;

import feign.Logger;
import feign.Logger.Level;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients(basePackages = "com.zjl.archetype.web.infra.feign")
public class FeignConfiguration {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Level.FULL;
    }

}
