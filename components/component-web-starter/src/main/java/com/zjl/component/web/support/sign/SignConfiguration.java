package com.zjl.component.web.support.sign;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

public class SignConfiguration {

    @Bean
    @ConditionalOnMissingBean(SignValidateFilter.class)
    public SignValidateFilter signValidateFilter() {
        return new SignValidateFilter();
    }

    @Bean
    public FilterRegistrationBean registrationProjectFilter(SignValidateFilter signValidateFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(signValidateFilter);
        registration.addUrlPatterns("/internal/*");
        return registration;
    }

}
