package com.zjl.archetype.web.domain.customer.service;

import com.zjl.archetype.web.domain.customer.Customer;

public interface CustomerService {

    Customer getByCustomerId(String customerId);

    Customer getByCustomerName(String customerName);

    Long addCustomer(Customer customer);
}
