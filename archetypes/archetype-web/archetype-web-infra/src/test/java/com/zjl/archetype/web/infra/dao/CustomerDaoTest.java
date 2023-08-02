package com.zjl.archetype.web.infra.dao;

import javax.annotation.Resource;

import com.zjl.archetype.web.infra.SpringApplicationTest;
import org.junit.Ignore;
import org.junit.Test;

@Ignore // 默认不进行容器启动的测试，infra层常年不会更改。除了dao
public class CustomerDaoTest implements SpringApplicationTest {

    @Resource
    private CustomerDao customerDao;

    @Test
    public void testFindByID() {
        System.out.println("Write your test here");
    }

    @Test
    public void testGetLatest() {
        CustomerDO zjlUser = customerDao.getByCustomerId(DEFAULT_USER_ID);
        System.out.println(zjlUser);
    }
}
