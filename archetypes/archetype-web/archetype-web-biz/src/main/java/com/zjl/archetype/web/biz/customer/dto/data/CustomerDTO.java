package com.zjl.archetype.web.biz.customer.dto.data;

import javax.validation.constraints.NotEmpty;

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
