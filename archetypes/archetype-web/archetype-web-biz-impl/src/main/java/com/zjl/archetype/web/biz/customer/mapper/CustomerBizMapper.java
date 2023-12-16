package com.zjl.archetype.web.biz.customer.mapper;

import com.zjl.archetype.web.client.customer.dto.CustomerDTO;
import com.zjl.archetype.web.domain.customer.dto.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerBizMapper {

    CustomerBizMapper INSTANCE = Mappers.getMapper(CustomerBizMapper.class);

    Customer toCustomer(CustomerDTO customerDTO);

    CustomerDTO formCustomer(Customer customer);

}
