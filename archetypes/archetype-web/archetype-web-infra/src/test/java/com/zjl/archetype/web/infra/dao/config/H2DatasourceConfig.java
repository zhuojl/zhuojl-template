package com.zjl.archetype.web.infra.dao.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
@ConditionalOnProperty(name = "spring.datasource.driverClassName", havingValue = "org.h2.Driver")
public class H2DatasourceConfig {

    @Value("classpath:h2-init/db-schema.sql")
    private Resource schemaScript;

    @Value("classpath:h2-init/db-test-data.sql")
    private Resource dataScript;

    @Bean
    public DataSourceInitializer dataSourceInitializer(final DataSource dataSource) {
        // 数据库初始化
        final DataSourceInitializer initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(databasePopulator());
        return initializer;
    }

    private DatabasePopulator databasePopulator() {
        final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        // 初始化脚本：schemaScript schema脚本，dataScript 数据脚本
        populator.addScripts(schemaScript, dataScript);
        return populator;
    }
}
