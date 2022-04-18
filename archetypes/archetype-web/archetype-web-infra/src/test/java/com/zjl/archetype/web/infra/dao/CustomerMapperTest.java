package com.zjl.archetype.web.infra.dao;

import javax.annotation.Resource;

import com.zjl.archetype.web.infra.BaseApplicationTest;
import org.junit.Test;

public class CustomerMapperTest extends BaseApplicationTest {

    @Resource
    private CustomerMapper customerMapper;

    @Test
    public void testFindByID() {
        System.out.println("Write your test here");
    }

    @Test
    public void testGetLatest() {
        CustomerDO zjlUser = customerMapper.getByCustomerId(DEFAULT_USER_ID);
        System.out.println(zjlUser);
    }
}
