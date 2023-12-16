package com.zjl.archetype.web.biz.customer.service;

import java.util.List;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.CustomerListByNameQry;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import com.zjl.component.common.model.Response;

public interface CustomerBizService {

    Response<Long> addCustomer(CustomerAddCmd customerAddCmd);

    Response<List<CustomerDTO>> listByName(CustomerListByNameQry customerListByNameQry);

}
