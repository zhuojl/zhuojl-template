package com.zjl.archetype.web.domain.customer.service;

import com.zjl.archetype.web.domain.customer.Customer;
import com.zjl.archetype.web.domain.customer.mapper.CustomerMapper;
import com.zjl.archetype.web.infra.dao.customer.CustomerDO;
import com.zjl.archetype.web.infra.dao.customer.CustomerDao;
import com.zjl.archetype.web.infra.event.EventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private EventPublisher eventPublisher;

    public Customer getByById(String customerId) {
        CustomerDO customerDO = customerDao.getByCustomerId(customerId);
        //Convert to Customer
        return CustomerMapper.INSTANCE.fromCustomerDO(customerDO);
    }

    @Override
    public Customer getByCompanyName(String customerName) {
        CustomerDO customerDO = customerDao.getByCustomerId(customerName);
        return CustomerMapper.INSTANCE.fromCustomerDO(customerDO);
    }

    @Override
    @Transactional
    public String addCustomer(Customer customer) {
        CustomerDO customerDO = new CustomerDO();
        // convert customer 2 CustomerDO
        customerDao.insert(customerDO);
        // event publish 领域逻辑时间在领域层发送
        eventPublisher.publish(new Object());

        return customerDO.getCustomerId();
    }
}
