package com.zjl.archetype.web.domain.customer.dto;

import com.zjl.archetype.web.constants.customer.CustomerType;
import lombok.Data;

//Domain Entity can choose to extend the domain model which is used for DTO
@Data
public class Customer {

    private Long id;
    private String customerId;
    private String customerName;
    private String memberId;
    private String globalId;
    private long registeredCapital;

    private CustomerType customerType;

}
