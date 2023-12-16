package com.zjl.archetype.web.biz.customer.service;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.CustomerListByNameQry;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import com.zjl.component.common.model.Response;
import java.util.List;

public interface CustomerBizService {

    Response<Long> addCustomer(CustomerAddCmd customerAddCmd);

    Response<List<CustomerDTO>> listByName(CustomerListByNameQry customerListByNameQry);

}
