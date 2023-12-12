package com.zjl.archetype.web.infra.client;

import com.zjl.archetype.web.infra.client.dto.UserDto;
import com.zjl.archetype.web.infra.dao.customer.CustomerDO;
import com.zjl.archetype.web.infra.feign.FeignTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 三方系统请求
 */
@Component
public class UserClient {

    @Autowired
    private FeignTest feignTest;

    public UserDto dummy() {
        CustomerDO customerDO = feignTest.test(new HashMap());
        UserDto userDto = new UserDto();
        userDto.setCustomerId(customerDO.getCustomerId());
        userDto.setUserId("userId");
        return userDto;
    }
}
