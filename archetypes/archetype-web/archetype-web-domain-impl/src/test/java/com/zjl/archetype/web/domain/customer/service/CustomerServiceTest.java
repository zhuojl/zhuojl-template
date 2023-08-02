package com.zjl.archetype.web.domain.customer.service;

import com.zjl.archetype.web.domain.customer.Customer;
import com.zjl.archetype.web.infra.dao.CustomerDO;
import com.zjl.archetype.web.infra.dao.CustomerMapper;
import com.zjl.archetype.web.infra.event.EventPublisher;
import com.zjl.archetype.web.infra.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class) // 和 infra 不一致？ infra使用 ExtendWith 方式，
public class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Spy
    private CustomerMapper customerMapper;
    @Mock
    private EventPublisher eventPublisher;

    @Test
    public void testGetByById() throws IOException {
        CustomerDO customerDO = JsonUtil.parseJsonFromClassPathFile("test/domain/customer/CustomerDO.json", CustomerDO.class);
        Mockito.when(customerMapper.getByCustomerId("test")).thenReturn(customerDO);
        Customer customer = customerService.getByById("test");
        Assert.assertNotNull(customer);
        Assert.assertEquals(customerDO.getCustomerId(), customer.getCustomerId());

        Customer customer2 = customerService.getByById("test1");
        Assert.assertNull(customer2);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        String customerId = customerService.addCustomer(customer);
    }
}
