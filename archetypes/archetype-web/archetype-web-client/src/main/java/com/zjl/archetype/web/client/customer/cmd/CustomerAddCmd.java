package com.zjl.archetype.web.client.customer.cmd;

import com.zjl.archetype.web.client.customer.dto.CustomerDTO;
import com.zjl.component.common.model.Command;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomerAddCmd extends Command {

    @NotNull
    private CustomerDTO customer;

}
