package com.zjl.archetype.web.biz.customer.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.zjl.archetype.web.biz.customer.dto.CustomerAddCmd;
import com.zjl.archetype.web.biz.customer.dto.CustomerListByNameQry;
import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import com.zjl.archetype.web.biz.customer.mapper.CustomerBizMapper;
import com.zjl.archetype.web.biz.customer.validator.CustomerValidator;
import com.zjl.archetype.web.domain.customer.Customer;
import com.zjl.archetype.web.domain.customer.service.CustomerService;
import com.zjl.archetype.web.infra.exception.BizErrorEnum;
import com.zjl.component.common.exception.ExceptionFactory;
import com.zjl.component.common.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerBizServiceImpl implements CustomerBizService {

    @Autowired
    private CustomerService customerService;

    @Override
    @Transactional
    public Response<Long> addCustomer(CustomerAddCmd cmd) {
        CustomerValidator.validate(cmd.getCustomer());
        Customer customer = customerService.getByCustomerName(cmd.getCustomer().getCustomerName());
        if (Objects.nonNull(customer)) {
            throw ExceptionFactory.badRequestException(BizErrorEnum.COMPANY_NAME_REPEAT);
        }
        Customer save = CustomerBizMapper.INSTANCE.toCustomer(cmd.getCustomer());
        Long result = customerService.addCustomer(save);
        return Response.success(result);
    }

    @Override
    public Response<List<CustomerDTO>> listByName(CustomerListByNameQry customerListByNameQry) {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        Customer customer = customerService.getByCustomerId("zjl");

        customerDTOList.add(CustomerBizMapper.INSTANCE.formCustomer(customer));
        return Response.success(customerDTOList);
    }
}
