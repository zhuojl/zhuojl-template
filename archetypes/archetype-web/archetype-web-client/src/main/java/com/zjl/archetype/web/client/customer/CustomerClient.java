package com.zjl.archetype.web.client.customer;

import java.util.List;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.CustomerListByNameQry;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import com.zjl.component.common.model.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CustomerClient {

    @PostMapping(value = "/customer")
    Response<Long> addCustomer(@Valid @RequestBody CustomerAddCmd customerAddCmd);

    @GetMapping(value = "/customer")
    Response<List<CustomerDTO>> listByName(@Valid CustomerListByNameQry customerListByNameQry);
}
