package com.zjl.archetype.web.domain.customer.service;

import com.zjl.archetype.web.domain.customer.Customer;
import com.zjl.archetype.web.infra.dao.CustomerDO;
import com.zjl.archetype.web.infra.dao.CustomerMapper;
import com.zjl.archetype.web.infra.event.EventPublisher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerMapper customerMapper;
    @Mock
    private EventPublisher eventPublisher;

    @Test
    public void testGetByById() {
        Mockito.when(customerMapper.getByCustomerId(Mockito.anyString())).thenReturn(new CustomerDO());
        Customer customer = customerService.getByById("test");
        Assert.assertNotNull(customer);
    }

    @Test
    public void testAddCustomer() {
        Customer customer = new Customer();
        String customerId = customerService.addCustomer(customer);
    }
}
