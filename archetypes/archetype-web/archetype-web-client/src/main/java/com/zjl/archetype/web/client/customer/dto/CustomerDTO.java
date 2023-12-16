package com.zjl.archetype.web.client.customer.dto;

import lombok.Data;

@Data
public class CustomerDTO {

    private Long id;
    private String customerId;
    private String customerName;
    private String memberId;
    private String globalId;
    private long registeredCapital;
}
