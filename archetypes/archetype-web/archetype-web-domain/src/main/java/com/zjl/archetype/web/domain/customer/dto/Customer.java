package com.zjl.archetype.web.domain.customer.dto;

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

}
