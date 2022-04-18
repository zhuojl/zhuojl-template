package com.zjl.archetype.web.client.customer;

import java.util.List;

import com.zjl.component.dto.Response;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.CustomerListByNameQry;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerClient {

    @PostMapping(value = "/customer")
    Response<String> addCustomer(@RequestBody CustomerAddCmd customerAddCmd);

    @GetMapping(value = "/customer")
    Response<List<CustomerDTO>> listByName(CustomerListByNameQry customerListByNameQry);
}