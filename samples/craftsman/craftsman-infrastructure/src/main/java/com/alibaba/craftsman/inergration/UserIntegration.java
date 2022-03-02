package com.alibaba.craftsman.inergration;

import com.alibaba.craftsman.client.UserClient;
import com.alibaba.craftsman.client.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserIntegration {

    @Autowired
    private UserClient userClient;

    public UserDto getById(Long id) {
        return userClient.getById(id);
    }
}
