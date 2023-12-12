package com.zjl.archetype.web.domain.customer.service;

import com.zjl.archetype.web.domain.customer.Customer;
import com.zjl.archetype.web.infra.dao.customer.CustomerDO;
import com.zjl.archetype.web.infra.dao.customer.CustomerDao;
import com.zjl.archetype.web.infra.event.EventPublisher;
import com.zjl.archetype.web.infra.util.JsonUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

//@RunWith(MockitoJUnitRunner.class) // junit 4
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Spy
    private CustomerDao customerDao;
    @Mock
    private EventPublisher eventPublisher;

    @Test
    public void testGetByById() throws IOException {
        CustomerDO customerDO = JsonUtil.parseJsonFromClassPathFile("test/domain/customer/CustomerDO.json", CustomerDO.class);
        Mockito.when(customerDao.getByCustomerId("test")).thenReturn(customerDO);
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
