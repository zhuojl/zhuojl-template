package com.zjl.archetype.web.infra;

import org.junit.Ignore;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = SpringApplicationTest.DemoApplication.class, properties = {
    "spring.config.location:classpath:/application-test.properties"
})
@ExtendWith(SpringExtension.class)
@Ignore // 默认不进行容器启动的测试，infra层常年不会更改。除了dao
public interface SpringApplicationTest {

    @SpringBootApplication
    static class DemoApplication {

    }

}
