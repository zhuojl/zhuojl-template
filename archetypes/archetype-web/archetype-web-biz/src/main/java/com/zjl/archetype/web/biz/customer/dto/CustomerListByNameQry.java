package com.zjl.archetype.web.biz.customer.dto;

import javax.validation.constraints.NotBlank;

import com.zjl.component.common.model.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerListByNameQry extends DTO {
    @NotBlank
    private String name;
}
