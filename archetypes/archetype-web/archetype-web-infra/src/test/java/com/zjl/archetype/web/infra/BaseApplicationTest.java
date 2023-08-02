package com.zjl.archetype.web.infra;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = BaseApplicationTest.DemoApplication.class, properties = {
    "spring.config.location:classpath:/application-test.properties"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class BaseApplicationTest {

    public static final String DEFAULT_USER_NAME = "zjl";
    public static final String DEFAULT_USER_ID = "zjlId";

    @SpringBootApplication
    static class DemoApplication {

    }
    @Test
    public void baseTest() {

    }

}
