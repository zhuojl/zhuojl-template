package com.zjl.archetype.web.infra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zjl.archetype.web.infra.dao")
@EnableFeignClients(basePackages = "com.zjl.archetype.web.infra.feign")
public class InfraConfiguration {

}
