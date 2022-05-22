package com.zjl.component.web.support;

import javax.servlet.Filter;

import com.zjl.component.sign.Md5SignValidateFilter;
import com.zjl.component.sign.Md5Signer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@ConditionalOnClass(Filter.class)
public class SignAutoConfiguration {

    @Value("${common.web.sign.urlPatterns:/internal/*}")
    private String urlPatterns;

    @Bean
    public Md5Signer md5Signer() {
        return new Md5Signer();
    }

    @Bean
    @ConditionalOnMissingBean(Md5SignValidateFilter.class)
    public Md5SignValidateFilter signValidateFilter() {
        return new Md5SignValidateFilter();
    }

    @Bean
    public FilterRegistrationBean registrationProjectFilter(Md5SignValidateFilter md5DeSignValidateFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(md5DeSignValidateFilter);
        registration.addUrlPatterns(urlPatterns);
        return registration;
    }

}
