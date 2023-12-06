package com.zjl.archetype.web.adapter.web;

import java.util.List;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.CustomerListByNameQry;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import com.zjl.archetype.web.biz.customer.service.CustomerBizService;
import com.zjl.archetype.web.client.customer.CustomerClient;
import com.zjl.component.common.exception.CommonErrorEnum;
import com.zjl.component.common.exception.ExceptionFactory;
import com.zjl.component.common.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 参数校验
@Validated
public class CustomerController implements CustomerClient {

    @Autowired
    private CustomerBizService customerBizService;

    @GetMapping(value = "/helloworld")
    public String helloWorld() throws Exception {
        throw new Exception("test");
    }

    @PostMapping(value = "/helloworld")
    public String helloWorld2() {
        throw ExceptionFactory.badRequestException(CommonErrorEnum.INNER_ERROR);
        //return "Hello, welcome to COLA world!";
    }

    public Response<String> addCustomer(CustomerAddCmd customerAddCmd) {
        return customerBizService.addCustomer(customerAddCmd);
    }

    public Response<List<CustomerDTO>> listByName(CustomerListByNameQry customerListByNameQry) {
        return customerBizService.listByName(customerListByNameQry);
    }
}
