package com.zjl.archetype.web.biz.customer;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import com.zjl.archetype.web.biz.customer.service.CustomerBizServiceImpl;
import com.zjl.archetype.web.domain.customer.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerBizServiceTest {

    @InjectMocks
    private CustomerBizServiceImpl customerBizService;
    @Mock
    private CustomerService customerService;

    @Test
    public void testAddCustomer() {
        CustomerAddCmd customerAddCmd = new CustomerAddCmd();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId("DEFAULT_USER_ID");
        customerDTO.setCustomerName("DEFAULT_USER_NAME");
        customerDTO.setCustomerType("");
        customerAddCmd.setCustomerDTO(customerDTO);
        customerBizService.addCustomer(customerAddCmd);
    }
}
