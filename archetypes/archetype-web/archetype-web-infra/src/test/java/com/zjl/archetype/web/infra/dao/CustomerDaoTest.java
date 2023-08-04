package com.zjl.archetype.web.infra.dao;

import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class CustomerDaoTest implements MysqlApplicationTest{

    @Resource
    private CustomerDao customerDao;

    @Test
    public void testFindByID() {
        System.out.println("Write your test here");
    }

    @Test
    public void testGetLatest() {
        CustomerDO zjlUser = customerDao.getByCustomerId("zjlId");
        System.out.println(zjlUser);
    }
}
