package com.zjl.archetype.web.biz.customer.dto;

import javax.validation.constraints.NotNull;

import com.zjl.archetype.web.biz.customer.dto.data.CustomerDTO;
import lombok.Data;

@Data
public class CustomerAddCmd {

    @NotNull
    private CustomerDTO customerDTO;

}
