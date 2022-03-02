package com.zhuojl.demo.domain.customer.gateway;

import com.zhuojl.demo.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
