package com.zjl.archetype.web.infra.client;

import com.zjl.archetype.web.infra.client.dto.UserDto;
import com.zjl.archetype.web.infra.dao.CustomerDO;
import com.zjl.archetype.web.infra.feign.FeignTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 会加载容器所有bean，需要mockBean的太多，为减少依赖，不推荐这种方式。
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Ignore
class UserClientTest {

    @Autowired
    private UserClient userClient;
    @MockBean
    private FeignTest feignTest;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this); // 初始化Mock对象
    }

    @Test
    void testDummy() {
        Mockito.when(feignTest.test(Mockito.any())).thenReturn(new CustomerDO());
        UserDto userDto = userClient.dummy();
        Assert.assertEquals("userId", userDto.getUserId());
    }

    /**
     * 如果有其他依赖就会启动不起来
     */
    @Deprecated
    @KafkaListener(topics = "TOPIC_NAME", groupId = "listener")
    private void listen(ConsumerRecord<String, String> consumerRecord) throws InterruptedException {
    }
}
