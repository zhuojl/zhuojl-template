package com.zjl.component.web.support;

import com.zjl.component.web.support.sign.Md5SignValidateFilter;
import com.zjl.component.web.support.sign.SignValidateFilter;
import javax.servlet.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(Filter.class)
@ConditionalOnProperty(prefix = "filter.sign.verify", value = "enabled", havingValue = "true")
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
