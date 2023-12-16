package com.zjl.archetype.web.biz.customer.dto;

import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerAddCmd {

    @NotNull
    private CustomerDTO customer;

}
