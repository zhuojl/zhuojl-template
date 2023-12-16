package com.zjl.archetype.web.biz.customer.validator;

import com.zjl.archetype.web.client.customer.dto.CustomerDTO;
import java.util.Objects;

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
