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

    public Customer getByCustomerId(String customerId) {
        CustomerDO customerDO = customerDao.getByCustomerId(customerId);
        //Convert to Customer
        return CustomerMapper.INSTANCE.fromCustomerDO(customerDO);
    }

    public Customer getById(Long id) {
        CustomerDO customerDO = customerDao.selectById(id);
        //Convert to Customer
        return CustomerMapper.INSTANCE.fromCustomerDO(customerDO);
    }

    @Override
    public Customer getByCompanyName(String customerName) {
        CustomerDO customerDO = customerDao.getByCompanyName(customerName);
        return CustomerMapper.INSTANCE.fromCustomerDO(customerDO);
    }

    @Override
    @Transactional
    public Long addCustomer(Customer customer) {
        CustomerDO customerDO = CustomerMapper.INSTANCE.toCustomerDO(customer);
        // convert customer 2 CustomerDO
        customerDao.insert(customerDO);
        // event publish 领域逻辑时间在领域层发送
        eventPublisher.publish(new Object());

        return customerDO.getId();
    }
}
