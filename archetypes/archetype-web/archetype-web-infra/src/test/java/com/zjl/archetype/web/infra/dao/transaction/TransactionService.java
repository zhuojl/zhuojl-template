package com.zjl.archetype.web.infra.dao.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TransactionService _self;

    @Transactional
    public void test() {
        _self.test2();
        try {
            _self.test3();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Transactional
    public void test2() {
        User user = new User();
        user.setAge(18);
        user.setName("xx");
        user.setEmail("xx");
        userMapper.insert(user);
    }

    @Transactional
    public void test3() {
        User user = new User();
        user.setAge(18);
        user.setName("xx");
        user.setEmail("xx");
        userMapper.insert(user);
        throw new RuntimeException("xxxx");
    }


}
