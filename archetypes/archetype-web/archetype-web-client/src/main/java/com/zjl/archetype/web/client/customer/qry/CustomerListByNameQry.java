package com.zjl.archetype.web.client.customer.qry;

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
