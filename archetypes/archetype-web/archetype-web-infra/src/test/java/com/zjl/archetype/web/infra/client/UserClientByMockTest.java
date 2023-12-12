package com.zjl.archetype.web.infra.client;

import com.zjl.archetype.web.infra.client.dto.UserDto;
import com.zjl.archetype.web.infra.dao.customer.CustomerDO;
import com.zjl.archetype.web.infra.feign.FeignTest;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//@RunWith(MockitoJUnitRunner.class) // junit 4
@ExtendWith(MockitoExtension.class)
class UserClientByMockTest {
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

    @Test
    void emptyTest() {
    }

}
