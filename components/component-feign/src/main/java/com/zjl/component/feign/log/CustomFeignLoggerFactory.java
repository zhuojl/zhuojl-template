package com.zjl.component.feign.log;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

/**
 * @author Venil Noronha
 */
public class CustomFeignLoggerFactory implements FeignLoggerFactory {

    private Logger logger;

    public CustomFeignLoggerFactory() {
    }

    @Override
    public Logger create(Class<?> type) {
        return this.logger != null ? this.logger : new FeignLogger(type);
    }

}
