package com.zjl.archetype.web.infra;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = SpringApplicationTest.DemoApplication.class, properties = {
    "spring.config.location:classpath:/application-test.properties"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Ignore // 默认不进行容器启动的测试，infra层常年不会更改。除了dao
public interface SpringApplicationTest {

    String DEFAULT_USER_NAME = "zjl";
    String DEFAULT_USER_ID = "zjlId";

    @SpringBootApplication
    static class DemoApplication {

    }

}
