package com.zjl.archetype.web.client.customer;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.CustomerListByNameQry;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import com.zjl.archetype.web.biz.customer.service.CustomerBizService;
import com.zjl.component.common.model.Response;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 参数校验
@Validated
public class CustomerController implements CustomerClient {

    @Autowired
    private CustomerBizService customerBizService;

    public Response<Long> addCustomer(CustomerAddCmd customerAddCmd) {
        return customerBizService.addCustomer(customerAddCmd);
    }

    public Response<List<CustomerDTO>> listByName(CustomerListByNameQry customerListByNameQry) {
        return customerBizService.listByName(customerListByNameQry);
    }
}
