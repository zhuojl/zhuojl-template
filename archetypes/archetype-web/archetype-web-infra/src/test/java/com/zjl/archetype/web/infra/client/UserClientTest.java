package com.zjl.archetype.web.infra.client;

import com.zjl.archetype.web.infra.client.dto.UserDto;
import com.zjl.archetype.web.infra.dao.customer.CustomerDO;
import com.zjl.archetype.web.infra.feign.FeignTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * 会加载容器所有bean，需要mockBean的太多，为减少依赖，不推荐这种方式。
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserClientTest.UserClientTestApplication.class)
public class UserClientTest {

    @Autowired
    private UserClient userClient;
    @MockBean
    private FeignTest feignTest;

    @Test
    public void testDummy() {
        Mockito.when(feignTest.test(Mockito.anyMap())).thenReturn(new CustomerDO());
        UserDto userDto = userClient.dummy();
        Assert.assertEquals("userId", userDto.getUserId());
    }

    @Test
    void emptyTest() {
    }

    @SpringBootApplication(scanBasePackages = "com.zjl.archetype.web.infra.client")
    static class UserClientTestApplication {

    }

}
