package com.zjl.archetype.web.domain.customer.mapper;

import com.zjl.archetype.web.domain.customer.Customer;
import com.zjl.archetype.web.infra.dao.CustomerDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDO toCustomerDO(Customer customer);

    Customer fromCustomerDO(CustomerDO customerDO);

}
