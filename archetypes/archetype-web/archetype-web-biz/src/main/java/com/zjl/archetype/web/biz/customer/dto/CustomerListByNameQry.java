package com.zjl.archetype.web.biz.customer.dto;

import com.zjl.component.common.model.DTO;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerListByNameQry extends DTO {

    @NotBlank
    private String name;
}
