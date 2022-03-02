package com.zhuojl.demo.domain.customer.gateway;

import com.zhuojl.demo.domain.customer.Credit;

//Assume that the credit info is in another distributed Service
public interface CreditGateway {
    Credit getCredit(String customerId);
}
