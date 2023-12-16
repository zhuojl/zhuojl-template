package com.zjl.archetype.web.client.customer.dto;

import com.zjl.archetype.web.constants.customer.CustomerType;
import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String customerId;
    private String customerName;
    private String memberId;
    private String globalId;
    private long registeredCapital;
    private CustomerType customerType;
}
