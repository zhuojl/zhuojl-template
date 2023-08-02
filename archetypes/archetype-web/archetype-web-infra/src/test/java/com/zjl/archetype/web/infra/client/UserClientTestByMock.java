package com.zjl.archetype.web.infra.client;

import com.zjl.archetype.web.infra.BaseMockTest;
import com.zjl.archetype.web.infra.client.dto.UserDto;
import com.zjl.archetype.web.infra.dao.CustomerDO;
import com.zjl.archetype.web.infra.feign.FeignTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

class UserClientTestByMock implements BaseMockTest {
    @InjectMocks
    private UserClient userClient;
    @Mock
    private FeignTest feignTest;

    @Test
    void testDummy() {
        Mockito.when(feignTest.test(Mockito.any())).thenReturn(new CustomerDO());
        UserDto userDto = userClient.dummy();
        Assert.assertEquals("userId", userDto.getUserId());
    }
}
