package com.zjl.archetype.web.domain.customer.service;

import com.zjl.archetype.web.domain.customer.Customer;

public interface CustomerService {
    Customer getByById(String customerId);

    Customer getByCompanyName(String customerName);

    String addCustomer(Customer customer);
}
