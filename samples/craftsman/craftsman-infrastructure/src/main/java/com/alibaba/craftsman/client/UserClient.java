package com.alibaba.craftsman.client;

import com.alibaba.craftsman.client.dto.UserDto;

import org.springframework.stereotype.Service;

/**
 * XXX 针对复杂rpc接口请求，增加防腐层，
 */
@Service
public class UserClient {


    public UserDto getById(Long userId) {
        UserDto userDto = new UserDto();
        return userDto;
    }
}
