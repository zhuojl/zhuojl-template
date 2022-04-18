package com.zjl.archetype.web.biz.customer.validator;

import java.util.Objects;

import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;

public class CustomerValidator {

    public CustomerValidator() {

    }

    public static void validate(CustomerDTO customerDTO) {
        if (Objects.isNull(customerDTO)) {
            throw new RuntimeException("param error");
        }
        //if (Strings.isEmpty(customerDTO.getCustomerName())) {
        //    throw new RuntimeException("param error");
        //}
    }
}
