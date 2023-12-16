package com.zjl.archetype.web.biz.customer.service;

import com.zjl.archetype.web.client.customer.cmd.CustomerAddCmd;
import com.zjl.archetype.web.client.customer.dto.CustomerDTO;
import com.zjl.archetype.web.client.customer.qry.CustomerListByNameQry;
import com.zjl.component.common.model.Response;
import java.util.List;

public interface CustomerBizService {

    Response<Long> addCustomer(CustomerAddCmd customerAddCmd);

    Response<List<CustomerDTO>> listByName(CustomerListByNameQry customerListByNameQry);

}
