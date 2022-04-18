package com.zjl.archetype.web.infra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zjl.archetype.web.infra.dao")
public class InfraConfiguration {

}
