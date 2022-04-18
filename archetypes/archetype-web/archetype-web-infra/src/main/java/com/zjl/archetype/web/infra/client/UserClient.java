package com.zjl.archetype.web.infra.client;

import com.zjl.archetype.web.infra.client.dto.UserDto;

/**
 * 三方系统请求
 */
public class UserClient {

    public UserDto dummy() {
        return new UserDto();
    }
}
