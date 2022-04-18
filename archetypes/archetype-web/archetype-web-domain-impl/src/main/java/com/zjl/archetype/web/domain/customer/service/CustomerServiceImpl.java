package com.zjl.archetype.web.domain.customer.service;

import com.zjl.archetype.web.domain.customer.Customer;
import com.zjl.archetype.web.infra.dao.CustomerDO;
import com.zjl.archetype.web.infra.dao.CustomerMapper;
import com.zjl.archetype.web.infra.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private EventPublisher eventPublisher;

    public Customer getByById(String customerId) {
        CustomerDO customerDO = customerMapper.getByCustomerId(customerId);
        //Convert to Customer
        return com.zjl.archetype.web.domain.customer.mapper.CustomerMapper.INSTANCE.fromCustomerDO(customerDO);
    }

    @Override
    public Customer getByCompanyName(String customerName) {
        CustomerDO customerDO = customerMapper.getByCompanyName(customerName);
        return com.zjl.archetype.web.domain.customer.mapper.CustomerMapper.INSTANCE.fromCustomerDO(customerDO);
    }

    @Override
    public String addCustomer(Customer customer) {
        CustomerDO customerDO = new CustomerDO();
        // convert customer 2 CustomerDO
        customerMapper.save(customerDO);
        // event publish 领域逻辑时间在领域层发送
        eventPublisher.publish(new Object());

        return customerDO.getCustomerId();
    }
}
