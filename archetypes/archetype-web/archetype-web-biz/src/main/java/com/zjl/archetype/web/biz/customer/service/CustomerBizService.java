package com.zjl.archetype.web.biz.customer.service;

import java.util.List;

import com.zjl.component.dto.Response;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.CustomerListByNameQry;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;

public interface CustomerBizService {

    Response<String> addCustomer(CustomerAddCmd customerAddCmd);

    Response<List<CustomerDTO>> listByName(CustomerListByNameQry customerListByNameQry);

}
