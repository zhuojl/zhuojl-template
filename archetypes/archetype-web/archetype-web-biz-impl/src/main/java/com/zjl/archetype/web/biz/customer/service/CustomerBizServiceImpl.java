package com.zjl.archetype.web.biz.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.zjl.component.dto.Response;
import com.zjl.component.exception.BizException;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.CustomerListByNameQry;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import com.zjl.archetype.web.biz.customer.dto.data.ErrorCode;
import com.zjl.archetype.web.biz.customer.mapper.CustomerBizMapper;
import com.zjl.archetype.web.biz.customer.validator.CustomerValidator;
import com.zjl.archetype.web.domain.customer.Customer;
import com.zjl.archetype.web.domain.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerBizServiceImpl implements CustomerBizService {

    @Autowired
    private CustomerService customerService;

    @Override
    public Response<String> addCustomer(CustomerAddCmd cmd) {
        CustomerValidator.validate(cmd.getCustomerDTO());
        Customer customer = customerService.getByCompanyName(cmd.getCustomerDTO().getCustomerName());
        if (Objects.nonNull(customer)) {
            throw new BizException(ErrorCode.B_CUSTOMER_companyNameConflict.getErrCode(), "公司名冲突");
        }
        Customer save = CustomerBizMapper.INSTANCE.toCustomer(cmd.getCustomerDTO());
        return Response.of(customerService.addCustomer(save));
    }

    @Override
    public Response<List<CustomerDTO>> listByName(CustomerListByNameQry customerListByNameQry) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Customer customer = customerService.getByById("zjl");

        customerDTOList.add(CustomerBizMapper.INSTANCE.formCustomer(customer));
        return Response.of(customerDTOList);
    }
}