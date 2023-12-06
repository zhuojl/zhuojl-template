package com.zjl.component.web.support;

import com.zjl.component.web.support.sign.Md5SignValidateFilter;
import com.zjl.component.web.support.sign.SignValidateFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

@ConditionalOnClass(Filter.class)
public class SignAutoConfiguration {

    @Value("${common.web.sign.urlPatterns:/internal/*}")
    private String urlPatterns;

    @Bean
    @ConditionalOnMissingBean(SignValidateFilter.class)
    public SignValidateFilter signValidateFilter() {
        return new Md5SignValidateFilter();
    }

    @Bean
    public FilterRegistrationBean registrationProjectFilter(SignValidateFilter signValidateFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(signValidateFilter);
        registration.addUrlPatterns(urlPatterns);
        return registration;
    }

}
