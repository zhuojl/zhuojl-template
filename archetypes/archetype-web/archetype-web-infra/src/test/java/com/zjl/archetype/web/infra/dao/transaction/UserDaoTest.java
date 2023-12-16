package com.zjl.archetype.web.infra.dao.transaction;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.zjl.archetype.web.infra.dao.MysqlApplicationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;

public class UserDaoTest implements MysqlApplicationTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
        userList.forEach(System.out::println);

        userList.get(0).setId(null);
        userMapper.batchInsert(Collections.singletonList(userList.get(0)));

        userList = userMapper.selectList(null);
        System.out.println(userList);
    }


    @Autowired
    private TransactionService transactionService;
    @Test
    public void testGetByCustomerName() {
        transactionService.test();

        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.isTrue(5 == userList.size(), "");
    }

}
