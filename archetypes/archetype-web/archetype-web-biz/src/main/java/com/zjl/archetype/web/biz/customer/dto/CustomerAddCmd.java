package com.zjl.archetype.web.biz.customer.dto;

import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import com.zjl.component.common.model.Command;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerAddCmd extends Command {

    @NotNull
    private CustomerDTO customer;

}
