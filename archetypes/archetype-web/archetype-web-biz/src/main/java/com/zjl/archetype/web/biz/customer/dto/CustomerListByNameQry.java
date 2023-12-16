package com.zjl.archetype.web.biz.customer.dto;

import com.zjl.component.common.model.Query;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomerListByNameQry extends Query {

    @NotBlank
    private String name;
}
