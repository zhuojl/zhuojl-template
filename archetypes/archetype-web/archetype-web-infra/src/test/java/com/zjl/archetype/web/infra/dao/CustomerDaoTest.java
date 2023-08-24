package com.zjl.archetype.web.infra.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    }  @Test
    public void testInsert() {

        CustomerDO customerDO = new CustomerDO();
        customerDO.setCustomerId("xxx");
        customerDO.setMemberId("xxx");
        customerDO.setGlobalId( "customer.getGd" );
        customerDO.setRegisteredCapital(2L );
        customerDO.setCompanyName( "customer()" );

        customerDao.insert(customerDO);
        CustomerDO customerDO2 = customerDao.getByCustomerId("xxx");
        System.out.println(customerDO2);
    }
}
