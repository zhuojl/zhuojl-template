package com.zjl.archetype.web.domain.customer.service

import com.zjl.archetype.web.domain.customer.dto.Customer
import com.zjl.archetype.web.infra.dao.customer.CustomerDO
import com.zjl.archetype.web.infra.dao.customer.CustomerDao
import com.zjl.archetype.web.infra.event.EventPublisher
import spock.lang.Specification

class CustomerServiceSpec extends Specification {

    def customerDao = Mock(CustomerDao)
    def eventPublisher = Mock(EventPublisher)
    def customerService = new CustomerServiceImpl(customerDao: customerDao, eventPublisher: eventPublisher)


    def testGetByById() {
        given:
        def customerDO = new CustomerDO(customerId: "customerId", customerName: "customerName");
        and:
        customerDao.getByCustomerId(_) >> customerDO;

        when:
        def customer = customerService.getByCustomerId("test");

        then:
        customer.customerId == customerDO.customerId
    }

    def testAddCustomer() {
        given:
        def customer = new Customer(customerId: "customerId", customerName: "customerName");
        and:
        customerDao.insert(_) >> 1;

        when:
        def customerId = customerService.addCustomer(customer);

        then:
        null == customerId
    }

}
