package com.zhuojl.demo.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.zhuojl.demo.dto.CustomerAddCmd;
import com.zhuojl.demo.dto.CustomerListByNameQry;
import com.zhuojl.demo.dto.data.CustomerDTO;

public interface CustomerServiceI {

    Response addCustomer(CustomerAddCmd customerAddCmd);

    MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry);
}
