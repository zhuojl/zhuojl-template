package com.zjl.archetype.web.client.customer;

import com.zjl.archetype.web.client.customer.cmd.CustomerAddCmd;
import com.zjl.archetype.web.client.customer.dto.CustomerDTO;
import com.zjl.archetype.web.client.customer.qry.CustomerListByNameQry;
import com.zjl.component.common.model.Response;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CustomerClient {

    @PostMapping(value = "/customer")
    Response<Long> addCustomer(@Valid @RequestBody CustomerAddCmd customerAddCmd);

    @GetMapping(value = "/customer")
    Response<List<CustomerDTO>> listByName(@Valid CustomerListByNameQry customerListByNameQry);
}
