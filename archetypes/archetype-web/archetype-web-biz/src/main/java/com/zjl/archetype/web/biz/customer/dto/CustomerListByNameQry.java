package com.zjl.archetype.web.biz.customer.dto;

import javax.validation.constraints.NotBlank;

import com.zjl.component.common.model.Query;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerListByNameQry extends Query {
    @NotBlank
    private String name;
}
