package com.zjl.archetype.web.infra.dao;

import org.junit.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = MysqlApplicationTest.MysqlApplication.class)
@TestPropertySource("classpath:/application-test.properties")
@ExtendWith(SpringExtension.class)
@Ignore // 默认不进行容器启动的测试，infra层常年不会更改。除了dao
public interface MysqlApplicationTest {

    @SpringBootApplication
    @MapperScan("com.zjl.archetype.web.infra.dao")
    class MysqlApplication {

    }

}
